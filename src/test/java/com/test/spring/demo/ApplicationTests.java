package com.test.spring.demo;

import com.prueba.meli.dataaccess.PlanetRepository;
import com.prueba.meli.model.Planet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ApplicationTests {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    void contextLoads() {
        assertThat(planetRepository.countPlanet()==3);

    }

}
