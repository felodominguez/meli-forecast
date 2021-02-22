package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Respuesta de la agenda de la tarea.", description = "Respuesta de la agenda de la tarea")
public class AddSchedulerTaskResponse {
    @ApiModelProperty(value = "Resultado del proceso.",example = "true")
    private boolean success;
    @ApiModelProperty(value = "Identificador de la tarea.",example = "6b679bbc-e85b")
    private String jobId;
    @ApiModelProperty(value = "Grupo que se le asignó a la tarea.",example = "task-jobs")
    private String jobGroup;
    @ApiModelProperty(value = "Mensaje descriptivo del resultado.",example = "Tarea agendada con éxito!")
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Fecha de la tarea")
    private Date taskDate;

    public AddSchedulerTaskResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AddSchedulerTaskResponse(boolean success, String jobId, String jobGroup, String message) {
        this.success = success;
        this.jobId = jobId;
        this.jobGroup = jobGroup;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}



