package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Respuesta de la agenda de la tarea.", description = "Respuesta de la agenda de la tarea")
public class SchedulerStatusResponse {
    @ApiModelProperty(value = "Activo",example = "true")
    private boolean activo;
    @ApiModelProperty(value = "Mensaje descriptivo del resultado.")
    private String message;

    List<AddSchedulerTaskResponse> listTask;


    public SchedulerStatusResponse(boolean activo, String message) {
        this.activo = activo;
        this.message = message;
        this.listTask = new ArrayList<>();
    }

    public List<AddSchedulerTaskResponse> getListTask() {
        return listTask;
    }

    public void setListTask(List<AddSchedulerTaskResponse> listTask) {
        this.listTask = listTask;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



