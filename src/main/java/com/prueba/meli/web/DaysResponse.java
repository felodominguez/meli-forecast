package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.meli.to.DayTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Respuesta del servicio de consulta del clima
 *
 * @author FD
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Respuesta con el resumen del pronóstico", description = "Respuesta con el resumen del pronóstico")
public class DaysResponse {
    @ApiModelProperty(value = "Días de sequía",position = 2)
    private Long droughtDays;
    @ApiModelProperty(value = "Días de lluvia",position = 3)
    private Long rainyDays;
    @ApiModelProperty(value = "Días de condiciones óptimas",position = 4)
    private Long optimalDays;
    @ApiModelProperty(value = "Días normales",position = 5)
    private Long normalDays;
    @ApiModelProperty(value = "Días de máximas presipitaciones",position = 6)
    private List<DayTO> rainiestDays;
    @ApiModelProperty(value = "Resultado del proceso.",example = "true",position = 0)
    private boolean success;
    @ApiModelProperty(value = "Mensaje descriptivo del resultado.",example = "Respuesta exitosa",position = 1)
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



