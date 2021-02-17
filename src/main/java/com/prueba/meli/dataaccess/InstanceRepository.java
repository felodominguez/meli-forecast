package com.prueba.meli.dataaccess;


import com.prueba.meli.model.Instance;
import com.prueba.meli.model.custom.IWeatherCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InstanceRepository extends CrudRepository<Instance,Long> {

    @Modifying
    @Query(value = "delete from day_planet",nativeQuery = true)
    int deleteInstanceDayPlanet();

    @Modifying
    @Query(value = "delete from day",nativeQuery = true)
    int deleteInstanceDay();

    @Modifying
    @Query(value = "delete from instance",nativeQuery = true)
    int deleteInstance();
}
