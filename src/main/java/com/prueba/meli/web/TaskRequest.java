package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ApiModel(value = "Parámetros de predicción.", description = "Parámetros para la generación de predicciones")
public class TaskRequest extends  GeneralTaskRequest{

    public TaskRequest() {
        super();

    }

}