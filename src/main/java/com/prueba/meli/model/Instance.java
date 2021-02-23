package com.prueba.meli.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prueba.meli.to.ParametersTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "instance")
public class Instance implements Serializable {
    @Id
    @SequenceGenerator(name="instance-seq", sequenceName = "instance_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="instance-seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;


    @Column(name = "init_vulcanos", nullable = false)
    private Integer initVulcanos;
    @Column(name = "init_ferengis", nullable = false)
    private Integer initFerengis;
    @Column(name = "init_betasoides", nullable = false)
    private Integer initBetasoides;
    @Column(name = "years", nullable = false)
    private Integer years;

    @Column(name = "avance_vulcanos", nullable = false)
    private Integer avanceVulcanos;
    @Column(name = "avance_ferengis", nullable = false)
    private Integer avanceFerengis;
    @Column(name = "avance_betasoides", nullable = false)
    private Integer avanceBetasoides;

    @Column(name = "distance_vulcanos", nullable = false)
    private Integer distanceVulcanos;
    @Column(name = "distance_ferengis", nullable = false)
    private Integer distanceFerengis;
    @Column(name = "distance_betasoides", nullable = false)
    private Integer distanceBetasoides;


    @OneToMany(mappedBy = "instance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> dayList;

    public Instance() {
    }

    public Instance(ParametersTO param) {
        this.date=ZonedDateTime.now();
        this.avanceBetasoides=param.getAvanceBetasoides();
        this.avanceFerengis=param.getAvanceFerengis();
        this.avanceVulcanos=param.getAvanceVulcanos();
        this.distanceBetasoides=param.getDistanceBetasoides();
        this.distanceFerengis=param.getDistanceFerengis();
        this.distanceVulcanos=param.getDistanceVulcanos();
        this.initFerengis=param.getInitFerengis();
        this.initVulcanos=param.getInitVulcanos();
        this.initBetasoides=param.getInitBetasoides();
        this.years=param.getYear();
        this.dayList=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
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


    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }
}
