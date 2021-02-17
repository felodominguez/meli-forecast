package com.prueba.meli.dataaccess;


import com.prueba.meli.model.Day;
import com.prueba.meli.model.custom.IWeatherCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DayRepository extends CrudRepository<Day,Long> {

    @Query("select d from Day d where d.day = ?1")
    Day findByDay(Long day);

    @Query(value = "select SUM(CASE WHEN  (d.weather = 0 )  THEN 1 ELSE 0 END) AS droughtCount " +
            ", SUM(CASE WHEN  (d.weather = 1 )  THEN 1 ELSE 0 END) AS rainyCount" +
            ", SUM(CASE WHEN  (d.weather = 2 )  THEN 1 ELSE 0 END) AS optimumCount" +
            ", SUM(CASE WHEN  (d.weather = 3 )  THEN 1 ELSE 0 END) AS normalCount" +
            " from day d ",nativeQuery = true)
    IWeatherCount countTotalDays();

    @Query(value = "select d from Day d where d.perimeter = (select max(d1.perimeter) from Day d1)")
    List<Day> getRainestDays();


}
