package com.prueba.meli.controller;


import com.prueba.meli.business.APIService;
import com.prueba.meli.model.custom.IWeatherCount;
import com.prueba.meli.web.DayResponse;
import com.prueba.meli.web.DaysResponse;
import com.prueba.meli.to.DayTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);


    @Autowired
    private APIService apiService;

    @GetMapping("/days")
    public ResponseEntity<DaysResponse> getWeatherInfo() {
        try {
            IWeatherCount calc = apiService.getDaysCount();
            DaysResponse response = new DaysResponse(true, null);
            response.setDroughtDays(calc.getDroughtCount());
            response.setOptimalDays(calc.getOptimumCount());
            response.setRainyDays(calc.getRainyCount());
            response.setNormalDays(calc.getNormalCount());
            response.setRainiestDays(apiService.getRainestDays());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            logger.error("Error " + ex.getMessage(), ex);
            DaysResponse response = new DaysResponse(false, "Error Desconocido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/days/{day}")
    public ResponseEntity<DayResponse> getDayInfo(
            /*@ApiParam(value = "Ingresar el día de predicción", allowableValues = "range[1,3600]", required = true)*/
            @PathVariable("day") Long day) {
        try {

            DayTO to = apiService.getDay(day);
            DayResponse response = new DayResponse(true, null);

            response.setDay(apiService.getDay(day));

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            logger.error("Error " + ex.getMessage(), ex);
            DayResponse response = new DayResponse(false, "Error Desconocido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}

