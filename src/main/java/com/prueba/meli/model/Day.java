package com.prueba.meli.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prueba.meli.to.DayTO;
import com.prueba.meli.to.PlanetTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "day")
public class Day implements Serializable {
    @Id
    @SequenceGenerator(name="day-seq", sequenceName = "day_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="day-seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "day",nullable = false)
    private Long day;

    @JsonIgnore
    @Column(name = "determinant")
    private Double determinant;

    @Column(name = "perimeter")
    private Double perimeter;

    @Column(name = "weather",nullable = false)
    private Weather weather;



    @JoinColumn(name = "instance_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Instance instance;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayPlanet> dayPlanetList;


    public Day() {
        this.setDayPlanetList(new ArrayList<>());
    }


    public Day(Long day) {
        this.day = day;
        this.setDayPlanetList(new ArrayList<>());

    }

    public Day(Long day, Double determinant, Double perimeter, Weather weather) {
        this.day = day;
        this.determinant = determinant;
        this.perimeter = perimeter;
        this.weather = weather;

    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public List<DayPlanet> getDayPlanetList() {
        return dayPlanetList;
    }

    public void setDayPlanetList(List<DayPlanet> dayPlanetList) {
        this.dayPlanetList = dayPlanetList;
    }

    public Double getDeterminant() {
        return determinant;
    }

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


    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public DayTO toTO(){
        DayTO to = new DayTO();
        to.setDeterminant(this.determinant);
        to.setWeather(this.weather);
        to.setWeatherDesctiption(this.weather.getMessage());
        to.setPerimeter(this.perimeter);
        to.setDay(this.day);
        DayPlanet pd = this.dayPlanetList.stream()
                .filter(dayPlanet -> "VUL".equals(dayPlanet.getPlanet().getCode()))
                .findAny()
                .orElse(null);
        to.setVulcanos(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
        pd = this.dayPlanetList.stream()
                .filter(dayPlanet -> "FER".equals(dayPlanet.getPlanet().getCode()))
                .findAny()
                .orElse(null);
        to.setFerengis(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
        pd = this.dayPlanetList.stream()
                .filter(dayPlanet -> "BET".equals(dayPlanet.getPlanet().getCode()))
                .findAny()
                .orElse(null);
        to.setBetasoides(new PlanetTO(pd.getAngle(), pd.getxPos(), pd.getyPos()));
        return to;
    }
}
