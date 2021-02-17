package com.prueba.meli.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author FD
 * Clase que representa las posicion de un planeta en un dia
 */
@Entity
@Table(name = "day_planet")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DayPlanet implements Serializable {

    @Id
    @SequenceGenerator(name="day-planet-seq", sequenceName = "day_planet_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="day-planet-seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "angle", nullable = false)
    private Integer angle;


    @Column(name = "xPos", nullable = false)
    private Double xPos;

    @Column(name = "yPos", nullable = false)
    private Double yPos;

    @JoinColumn(name = "planet_id", referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false)
    private Planet planet;


    @JoinColumn(name = "day_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Day day;

    public DayPlanet() {
    }

    public DayPlanet(Integer angle, Integer sunDistance, Double xPos, Double yPos, Planet planet, Day day) {
        this.angle = angle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.planet = planet;
        this.day = day;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Double getxPos() {
        return xPos;
    }

    public void setxPos(Double xPos) {
        this.xPos = xPos;
    }

    public Double getyPos() {
        return yPos;
    }

    public void setyPos(Double yPos) {
        this.yPos = yPos;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }




    public String printStatus(long day, String name) {
        return (name + " D:" + day + " A:" + this.angle + " X:" + this.xPos + " Y:" + this.yPos);
    }
}
