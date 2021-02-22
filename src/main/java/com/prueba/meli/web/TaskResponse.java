package com.prueba.meli.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Respuesta de ejecución de predicciones.", description = "Respuesta de ejecución de predicciones")
public class TaskResponse {

    @ApiModelProperty(value = "Resultado del proceso.",example = "true")
    private boolean success;
    @ApiModelProperty(value = "Mensaje descriptivo del resultado.",example = "Se generaron 3600 predicciones correctamente")
    private String message;

    public TaskResponse(boolean success, String message) {
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
}