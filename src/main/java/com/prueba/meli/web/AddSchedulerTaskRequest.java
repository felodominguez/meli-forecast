package com.prueba.meli.web;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AddSchedulerTaskRequest {

    //Angulo inicial de Vulcanos
    @NotNull
    Integer initVulcanos;
    //Angulo inicial de Ferengis
    @NotNull
    Integer initFerengis;
    //Angulo inicial de Betasoides
    @NotNull
    Integer initBetasoides;
    //Cantidad de años de prediccion
    @NotNull
    Integer year;
    //Avamce diario de Vulcanos
    @NotNull
    Integer avanceVulcanos;
    //Avance diario de Ferengis
    @NotNull
    Integer avanceFerengis;
    //Avance diario de Betasoides
    @NotNull
    Integer avanceBetasoides;
    //Distancia entre el Vulcanos y el Sol
    @NotNull
    Integer distanceVulcanos;
    //Distancia entre Ferengis y el Sol
    @NotNull
    Integer distanceFerengis;
    // Distancia entre el Betasoides y el Sol
    @NotNull
    Integer distanceBetasoides;

    //Fecha de ejecución de la tarea
    private LocalDateTime dateTime;

    //Zona horaria de la fecha de ejecución de la tarea
    private ZoneId timeZone;

    //Log de datos
    private Boolean logData;


    public AddSchedulerTaskRequest() {

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getLogData() {
        return logData;
    }

    public void setLogData(Boolean logData) {
        this.logData = logData;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }
}