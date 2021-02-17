package com.prueba.meli;

import com.prueba.meli.business.APIService;
import com.prueba.meli.business.Processor;
import com.prueba.meli.dataaccess.PlanetRepository;
import com.prueba.meli.model.Planet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private Processor processor;



    @Override
    public void run(String... args) throws Exception {
        logger.info("---------- Start Load Data -----------");
            Planet p1 = new Planet("VUL", "Vulcanos");
            planetRepository.save(p1);
            Planet p2 = new Planet("FER", "Ferengis");
            planetRepository.save(p2);
            Planet p3 = new Planet("BET", "Betasoides");
            planetRepository.save(p3);
            processor.calculate();
        logger.info("---------- Data load completed successfully -----------");
    }
}
