package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.meli.to.DayTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Respuesta del servicio de consulta del pronostico de un día
 *
 * @author FD
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Respuesta con la información del pronóstico", description = "Respuesta con la información del pronóstico")
public class DayResponse {
    @ApiModelProperty(value = "Resultado del proceso.",example = "true",position = 0)
    private boolean success;
    @ApiModelProperty(value = "Mensaje descriptivo del resultado.",example = "Respuesta exitosa",position = 1)
    private String message;
    @ApiModelProperty(value = "Información del día",position = 2)
    private DayTO day;

    public DayResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DayTO getDay() {
        return day;
    }

    public void setDay(DayTO day) {
        this.day = day;
    }
}



