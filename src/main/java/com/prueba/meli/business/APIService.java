package com.prueba.meli.business;


import com.prueba.meli.dataaccess.DayRepository;
import com.prueba.meli.model.Day;
import com.prueba.meli.model.DayPlanet;
import com.prueba.meli.model.custom.IWeatherCount;
import com.prueba.meli.to.DayTO;
import com.prueba.meli.to.PlanetTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Lógica de negocio de la api rest de consulta
 *
 * @author FD
 */
@Service
public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);

    @Autowired
    private DayRepository dayRepository;


    public IWeatherCount getDaysCount() throws Exception {
        try {
            return dayRepository.countTotalDays();

        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }

    public List<DayTO> getRainestDays() throws Exception {
        try {
            List<Day> listEntity = dayRepository.getRainestDays();
            List<DayTO> listTO = null;
            DayTO to;
            PlanetTO pTO;
            DayPlanet pd;
            if (listEntity != null && !listEntity.isEmpty()) {
                listTO = new ArrayList<>();
                for (Day d : listEntity) {
                    listTO.add(d.toTO());
                }
            }
            return listTO;

        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }

    public DayTO getDay(Long dayNumber) throws Exception {
        try {
            DayTO to = null;
            Day d = dayRepository.findByDay(dayNumber);
            if (d != null) {
                PlanetTO pTO;
                DayPlanet pd;
                to = new DayTO();
                to.setDeterminant(d.getDeterminant());
                to.setWeather(d.getWeather());
                to.setPerimeter(d.getPerimeter());
                to.setDay(d.getDay());
                pd = d.getDayPlanetList().stream()
                        .filter(dayPlanet -> "VUL".equals(dayPlanet.getPlanet().getCode()))
                        .findAny()
                        .orElse(null);
                to.setVulcanos(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
                pd = d.getDayPlanetList().stream()
                        .filter(dayPlanet -> "FER".equals(dayPlanet.getPlanet().getCode()))
                        .findAny()
                        .orElse(null);
                to.setFerengis(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
                pd = d.getDayPlanetList().stream()
                        .filter(dayPlanet -> "BET".equals(dayPlanet.getPlanet().getCode()))
                        .findAny()
                        .orElse(null);
                to.setBetasoides(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
            }

            return to;

        } catch (Exception e) {
            logger.error("Error " + e.getMessage(), e);
            throw e;
        }
    }


}
