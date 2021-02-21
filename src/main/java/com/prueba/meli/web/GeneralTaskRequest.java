package com.prueba.meli.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;


public  abstract class GeneralTaskRequest implements Serializable {

    @NotNull
    @ApiModelProperty(value = "Ángulo inicial de Vulcanos",example = "0",required = true)
    Integer initVulcanos;
    @NotNull
    @ApiModelProperty(value = "Ángulo inicial de Ferengis",example = "0",required = true)
    Integer initFerengis;
    @NotNull
    @ApiModelProperty(value = "Ángulo inicial de Betasoides",example = "0",required = true)
    Integer initBetasoides;
    @NotNull
    @ApiModelProperty(value = "Cantidad de años a predecir",example = "10",required = true)
    Integer year;
    @NotNull
    @ApiModelProperty(value = "Ángulo de avance diario de Vulcanos",example = "5",required = true)
    Integer avanceVulcanos;
    @NotNull
    @ApiModelProperty(value = "Ángulo de avance diario de Ferengis",example = "-1",required = true)
    Integer avanceFerengis;
    @NotNull
    @ApiModelProperty(value = "Ángulo de avance diario de Betasoides",example = "-3",required = true)
    Integer avanceBetasoides;
    @NotNull
    @ApiModelProperty(value = "Distancia entre el Sol y Vulcanos",example = "1000",required = true)
    Integer distanceVulcanos;
    @NotNull
    @ApiModelProperty(value = "Distancia entre el Sol y Ferengis",example = "500",required = true)
    Integer distanceFerengis;
    @NotNull
    @ApiModelProperty(value = "Distancia entre el Sol y Betasoides",example = "2000",required = true)
    Integer distanceBetasoides;

    //Log de datos
    @ApiModelProperty(value = "Indica si se escriben los resultados en el log del servidor",example = "true")
    private Boolean logData;


    public GeneralTaskRequest() {

    }

    public Integer getInitVulcanos() {
        return initVulcanos;
    }

    public void setInitVulcanos(Integer initVulcanos) {
        this.initVulcanos = initVulcanos;
    }

    public Integer getInitFerengis() {
        return initFerengis;
    }

    public void setInitFerengis(Integer initFerengis) {
        this.initFerengis = initFerengis;
    }

    public Integer getInitBetasoides() {
        return initBetasoides;
    }

    public void setInitBetasoides(Integer initBetasoides) {
        this.initBetasoides = initBetasoides;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAvanceVulcanos() {
        return avanceVulcanos;
    }

    public void setAvanceVulcanos(Integer avanceVulcanos) {
        this.avanceVulcanos = avanceVulcanos;
    }

    public Integer getAvanceFerengis() {
        return avanceFerengis;
    }

    public void setAvanceFerengis(Integer avanceFerengis) {
        this.avanceFerengis = avanceFerengis;
    }

    public Integer getAvanceBetasoides() {
        return avanceBetasoides;
    }

    public void setAvanceBetasoides(Integer avanceBetasoides) {
        this.avanceBetasoides = avanceBetasoides;
    }

    public Integer getDistanceVulcanos() {
        return distanceVulcanos;
    }

    public void setDistanceVulcanos(Integer distanceVulcanos) {
        this.distanceVulcanos = distanceVulcanos;
    }

    public Integer getDistanceFerengis() {
        return distanceFerengis;
    }

    public void setDistanceFerengis(Integer distanceFerengis) {
        this.distanceFerengis = distanceFerengis;
    }

    public Integer getDistanceBetasoides() {
        return distanceBetasoides;
    }

    public void setDistanceBetasoides(Integer distanceBetasoides) {
        this.distanceBetasoides = distanceBetasoides;
    }



    public Boolean getLogData() {
        return logData;
    }

    public void setLogData(Boolean logData) {
        this.logData = logData;
    }




}