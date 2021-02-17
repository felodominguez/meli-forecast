package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.meli.to.DayTO;

import java.util.List;

/**
 * Respuesta del servicio de consulta del clima
 *
 * @author FD
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DaysResponse {
    private Long droughtDays;
    private Long rainyDays;
    private Long optimalDays;
    private Long normalDays;
    private List<DayTO> rainiestDays;
    private boolean success;
    private String message;

    public DaysResponse(boolean success, String message) {
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

    public Long getDroughtDays() {
        return droughtDays;
    }

    public void setDroughtDays(Long droughtDays) {
        this.droughtDays = droughtDays;
    }

    public Long getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(Long rainyDays) {
        this.rainyDays = rainyDays;
    }

    public Long getOptimalDays() {
        return optimalDays;
    }

    public void setOptimalDays(Long optimalDays) {
        this.optimalDays = optimalDays;
    }

    public List<DayTO> getRainiestDays() {
        return rainiestDays;
    }

    public void setRainiestDays(List<DayTO> rainiestDays) {
        this.rainiestDays = rainiestDays;
    }

    public Long getNormalDays() {
        return normalDays;
    }

    public void setNormalDays(Long normalDays) {
        this.normalDays = normalDays;
    }
}



