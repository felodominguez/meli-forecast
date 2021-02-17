package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.meli.to.DayTO;

import java.util.List;

/**
 * Respuesta del servicio de consulta del pronostico de un día
 *
 * @author FD
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DayResponse {
    private boolean success;
    private String message;
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



