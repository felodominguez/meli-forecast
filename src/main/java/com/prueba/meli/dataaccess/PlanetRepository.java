package com.prueba.meli.dataaccess;


import com.prueba.meli.model.Planet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanetRepository extends CrudRepository<Planet,Long> {

    @Modifying
    @Query(value = "select count(*) from  planet",nativeQuery = true)
    int countPlanet();

    @Query(value = "SELECT p FROM Planet p")
    List<Planet> findAllPlanet();

}
