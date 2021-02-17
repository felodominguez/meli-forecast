package com.prueba.meli.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author FD
 * Clase que representa los planetas
 */
@Entity
@Table(name = "planet")
public class Planet implements Serializable {

    @Id
    @SequenceGenerator(name="planet-seq", sequenceName = "planet_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="planet-seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    public Planet() {

    }


    public Planet(String code, String name) {
        this.code = code;
        this.name = name;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
