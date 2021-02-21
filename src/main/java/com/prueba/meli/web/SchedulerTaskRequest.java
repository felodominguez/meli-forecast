package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ApiModel(value = "Parámetros de agenda de predicción.", description = "Parámetros para la generación de la agenda y las predicciones")
public class SchedulerTaskRequest  extends  GeneralTaskRequest{

    @NotNull
    @ApiModelProperty(value = "Fecha y hora de ejecución de la tarea",example = "2021-02-17T02:47",required = true)
    private LocalDateTime dateTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private ZoneId timeZone;

    @NotNull
    @ApiModelProperty(value = "Zona horaria de la hora de ejecución de la tarea",example = "America/Montevideo",dataType = "string",required = true)
    private String zonaHoraria;




    public SchedulerTaskRequest() {
        super();

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }



    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {

        this.timeZone = timeZone;

    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
        this.timeZone = ZoneId.of(zonaHoraria);
    }
}