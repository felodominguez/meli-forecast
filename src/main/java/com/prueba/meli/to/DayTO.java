package com.prueba.meli.to;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.meli.model.Day;
import com.prueba.meli.model.DayPlanet;
import com.prueba.meli.model.Planet;
import com.prueba.meli.model.Weather;
import com.prueba.meli.utils.MathUtils;
import com.prueba.meli.utils.WeatherUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa los dias
 *
 * @author FD
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DayTO implements Serializable {

    private Long day;
    private Double determinant;
    private Double perimeter;
    private Weather weather;
    private String weatherDesctiption;
    private Boolean maxPerimeter;

    private PlanetTO vulcanos;
    private PlanetTO ferengis;
    private PlanetTO betasoides;

    public DayTO() {
    }


    public DayTO(Long day, PlanetTO vulcanos, PlanetTO ferengis, PlanetTO betasoides) {
        this.day = day;
        this.vulcanos = vulcanos;
        this.ferengis = ferengis;
        this.betasoides = betasoides;
        this.maxPerimeter = Boolean.FALSE;
        calculate();
    }


    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Double getDeterminant() { return determinant; }

    public void setDeterminant(Double determinant) {
        this.determinant = determinant;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getWeatherDesctiption() {
        return weatherDesctiption;
    }

    public void setWeatherDesctiption(String weatherDesctiption) {
        this.weatherDesctiption = weatherDesctiption;
    }

    public PlanetTO getVulcanos() {
        return vulcanos;
    }

    public void setVulcanos(PlanetTO vulcanos) {
        this.vulcanos = vulcanos;
    }

    public PlanetTO getFerengis() {
        return ferengis;
    }

    public void setFerengis(PlanetTO ferengis) {
        this.ferengis = ferengis;
    }

    public PlanetTO getBetasoides() {
        return betasoides;
    }

    public void setBetasoides(PlanetTO betasoides) {
        this.betasoides = betasoides;
    }

    public Boolean getMaxPerimeter() {
        return maxPerimeter;
    }

    public void setMaxPerimeter(Boolean maxPerimeter) {
        this.maxPerimeter = maxPerimeter;
    }


    /**
     * Para un día calcula la posición de los planetas y calcula el determinante de la matriz que define si se encuentran alineados
     */
    public void calculate() {
        if (this.day > 1) {
            this.getVulcanos().calculate();
            this.getFerengis().calculate();
            this.getBetasoides().calculate();
        }
        double[][] matrix = {{this.getVulcanos().getxPos(), this.getVulcanos().getyPos(), 1},
                {this.getFerengis().getxPos(), this.getFerengis().getyPos(), 1},
                {this.getBetasoides().getxPos(), this.getBetasoides().getyPos(), 1}};
        //Calculo el determinante de la matriz - El determinante = 0 indica que los puntos están alienados.
        this.setDeterminant(MathUtils.determinant(matrix));

    }

    public void calculatePerimeter() {
        WeatherUtils.getPerimeter(this);
    }

    public Day toEntity(Planet vulcano,Planet ferengis,Planet betasoides){
        Day dayEnt = new Day();
        dayEnt.setDay(this.day);
        dayEnt.setDeterminant(this.determinant);
        dayEnt.setPerimeter(this.perimeter);
        dayEnt.setWeather(this.weather);

        DayPlanet dp = new DayPlanet();
        dp.setDay(dayEnt);
        dp.setPlanet(vulcano);
        dp.setAngle(this.vulcanos.getAngle());
        dp.setxPos(this.vulcanos.getxPos());
        dp.setyPos(this.vulcanos.getyPos());
        dayEnt.getDayPlanetList().add(dp);
        dp = new DayPlanet();
        dp.setDay(dayEnt);
        dp.setPlanet(ferengis);
        dp.setAngle(this.ferengis.getAngle());
        dp.setxPos(this.ferengis.getxPos());
        dp.setyPos(this.ferengis.getyPos());
        dayEnt.getDayPlanetList().add(dp);
        dp = new DayPlanet();
        dp.setDay(dayEnt);
        dp.setPlanet(betasoides);
        dp.setAngle(this.betasoides.getAngle());
        dp.setxPos(this.betasoides.getxPos());
        dp.setyPos(this.betasoides.getyPos());
        dayEnt.getDayPlanetList().add(dp);
        return dayEnt;

    }

    public String log() {
        StringBuffer str = new StringBuffer();
        str.append("\nDay " + this.day + " Weather " + this.weather + " Determinant " + this.determinant + "Perimeter " + this.perimeter + " MaxPerimeter " + this.maxPerimeter);
        str.append("\n\t VUL:" + this.vulcanos.log());
        str.append("\n\t FER:" + this.ferengis.log());
        str.append("\n\t BET:" + this.betasoides.log());
        str.append("\n");
        return str.toString();
    }

}