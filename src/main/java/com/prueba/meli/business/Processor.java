package com.prueba.meli.business;


import com.prueba.meli.dataaccess.InstanceRepository;
import com.prueba.meli.dataaccess.PlanetRepository;
import com.prueba.meli.model.*;
import com.prueba.meli.to.DayTO;
import com.prueba.meli.to.ParametersTO;
import com.prueba.meli.to.PlanetTO;
import com.prueba.meli.utils.WeatherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class Processor {

    private static final Logger logger = LoggerFactory.getLogger(Processor.class);

    @Autowired
    private InstanceRepository instanceRepo;

    @Autowired
    private PlanetRepository planetRepo;


    /**
     * Recibe los parametros y calcula todas las predicciones.
     *
     * @return Dias con las predicciones
     * @throws Exception
     */
    public Integer calculate(Integer initVulcanos, Integer initFerengis, Integer initBetasoides, Integer year, Integer avanceVulcanos,
                             Integer avanceFerengis, Integer avanceBetasoides, Integer distanceVulcanos, Integer distanceFerengis, Integer distanceBetasoides, Boolean logData) throws Exception {

        ParametersTO param = new ParametersTO();
        param.setInitVulcanos(initVulcanos);
        param.setInitFerengis(initFerengis);
        param.setInitBetasoides(initBetasoides);
        param.setYear(year);
        param.setAvanceVulcanos(avanceVulcanos);
        param.setAvanceFerengis(avanceFerengis);
        param.setAvanceBetasoides(avanceBetasoides);
        param.setDistanceVulcanos(distanceVulcanos);
        param.setDistanceFerengis(distanceFerengis);
        param.setDistanceBetasoides(distanceBetasoides);
        param.setLogData(logData);

        return generateData(param);

    }

    /**
     * Carga las configuraciones iniciales de un archivo YAML y calcula todas las predicciones.
     *
     * @return Cantidad de días calculados
     * @throws Exception
     */
    public Integer calculate() throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("com/prueba/meli/config/start.yml")) {
            ParametersTO param = yaml.loadAs(in, ParametersTO.class);
            return generateData(param);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * Recibe los parámetros y calcula todas las predicciones.
     *
     * @return Cantidad de días calculados
     * @throws Exception
     */
    private Integer generateData(ParametersTO param) throws Exception {
        try {
            logger.info("Start generateData");
            logger.info("Param " + param.toString());
            long start = new Date().getTime();
            long days = param.getYear() * 360;
            PlanetTO vulcanos = new PlanetTO(param.getInitVulcanos(), param.getDistanceVulcanos(), param.getAvanceVulcanos());
            PlanetTO ferengis = new PlanetTO(param.getInitFerengis(), param.getDistanceFerengis(), param.getAvanceFerengis());
            PlanetTO betasoides = new PlanetTO(param.getInitBetasoides(), param.getDistanceBetasoides(), param.getAvanceBetasoides());


            //int position;
            Map<Long, DayTO> diaHash = new HashMap<>();
            DayTO today;

            for (long i = 0; i < days; i++) {
                today = new DayTO(i + 1, vulcanos, ferengis, betasoides);
                if (WeatherUtils.droughtDay(today)) {
                    today.setWeather(Weather.SEQUIA);
                    today.setPerimeter(0.0);
                } else if (WeatherUtils.optimumDay(today)) {
                    today.setWeather(Weather.OPTIMO);
                    today.setPerimeter(0.0);
                } else if (WeatherUtils.rainyDay(today)) {
                    today.setWeather(Weather.LLUVIA);
                    //Calculo el perímetro
                    today.setPerimeter(WeatherUtils.getPerimeter(today));
                } else {
                    today.setWeather(Weather.NORMAL);
                }
                //A medida que los puntos se van alineado el determinante de la matriz se acerca a 0, luego de la alineación de los puntos el signo del determinante cambia.
                //Se entiende que cuando el determinante cambia de signo respecto al día anterior ocurre una alineación en algún momento del día anterior (No se da al momento del cálculo pero si dentro del período).
                //Se da por supuesto que los cálculos ocurren a la hora 0:00
                if (i > 0) {
                    if (WeatherUtils.optimumLastDay(today, diaHash.get(i))) {
                        //En función de ayer y hoy se modifica el día de ayer
                        diaHash.get(i).setWeather(Weather.OPTIMO);
                        diaHash.get(i).setPerimeter(0.0);
                    }
                }

                diaHash.put(i + 1, today);

            }

            logger.info("Generate " + (diaHash != null ? diaHash.size() : 0) + " records");
            saveData(diaHash, param);
            long fin = new Date().getTime();
            logger.info("Process end in  " + ((fin - start) / 1000) + " seconds.");

            return diaHash.size();
        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }

    }

    @Transactional
    public void saveData(Map<Long, DayTO> data, ParametersTO param) throws Exception {
        try {
            //Borro otras instancias en caso que existan
            long start = new Date().getTime();
            instanceRepo.deleteInstanceDayPlanet();
            instanceRepo.deleteInstanceDay();
            instanceRepo.deleteInstance();
            long fin = new Date().getTime();
            logger.info("Delete in  " + ((fin - start) / 1000) + " seconds.");
            for (Long d : data.keySet()) {
                System.out.println(data.get(d).log());
            }
            if (data != null && data.size() > 0) {
                List<Planet> listPlanet = (List<Planet>) planetRepo.findAll();
                Map<String, Planet> hashPlanet = new HashMap<>();
                for (Planet p : listPlanet) {
                    hashPlanet.put(p.getCode(), p);
                }

                Instance instance = new Instance(param);

                Day day = null;
                DayTO dayTO = null;
                List<DayPlanet> listDayPlanet = null;
                DayPlanet dp = null;
                for (Long d : data.keySet()) {
                    dayTO = data.get(d);
                    //System.out.println(dayTO.log());
                    day = dayTO.toEntity(hashPlanet.get("VUL"), hashPlanet.get("FER"), hashPlanet.get("BET"));
                    day.setInstance(instance);
                    instance.getDayList().add(day);
                }
                instanceRepo.save(instance);
                long finSave = new Date().getTime();
                logger.info("Save in  " + ((finSave - fin) / 1000) + " seconds.");

            } else {
                throw new Exception("Empty Data");
            }

        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void deleteById(Long id) throws Exception {
        try {
            planetRepo.deleteById(id);
        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void deleteAll() throws Exception {
        try {
            planetRepo.deleteAll();
        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }
}
