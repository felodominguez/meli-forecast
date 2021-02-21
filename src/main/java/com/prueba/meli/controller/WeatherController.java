package com.prueba.meli.controller;


import com.prueba.meli.business.APIService;
import com.prueba.meli.error.ErrorResponse;
import com.prueba.meli.error.RecordNotFoundException;
import com.prueba.meli.model.custom.IWeatherCount;
import com.prueba.meli.web.AddSchedulerTaskResponse;
import com.prueba.meli.web.DayResponse;
import com.prueba.meli.web.DaysResponse;
import com.prueba.meli.to.DayTO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@Api(value = "Controlador de consultas", description = "Controlador para el acceso a los datos", produces = "application/json")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);


    @Autowired
    private APIService apiService;

    @ApiOperation(notes = "Retorna el resumen de las predicciones",produces = "application/json", value = "Servicio para consultar el resumen de las predicciones")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = DaysResponse.class),
            @ApiResponse(code = 400, message = "Parámetros inválidos", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error general", response = ErrorResponse.class)
    })
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

    @ApiOperation(notes = "Retorna la predicción de un día específico",produces = "application/json", value = "Retorna la predicción de un día específico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = DayResponse.class),
            @ApiResponse(code = 400, message = "Parámetros inválidos", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error general", response = ErrorResponse.class)
    })
    @GetMapping("/day")
    public ResponseEntity<DayResponse> getDayInfo(
            @ApiParam(value = "Ingresar el día de predicción (1 a 3600)", name = "day",required = true,allowableValues = "range[1,3600]")
            @RequestParam("day") Long day) {
        DayTO to = apiService.getDay(day);
        if (to == null) {
            throw new RecordNotFoundException("No se encuentra pronóstico para el día : " + day);
        }
        DayResponse response = new DayResponse(true, null);

        response.setDay(to);
        return ResponseEntity.ok(response);
    }


}

