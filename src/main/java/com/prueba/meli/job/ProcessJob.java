package com.prueba.meli.job;

import com.prueba.meli.business.Processor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class ProcessJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(ProcessJob.class);

    @Autowired
    private Processor processor;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Date d = new Date();

            Long start=  new Date().getTime();
            logger.info("************Start******************* "+start);
            logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

            JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
            Integer initVulcanos = (Integer) jobDataMap.get("initVulcanos");
            Integer initFerengis = (Integer) jobDataMap.get("initFerengis");
            Integer initBetasoides = (Integer) jobDataMap.get("initBetasoides");
            Integer year = (Integer) jobDataMap.get("year");
            Integer avanceVulcanos = (Integer) jobDataMap.get("avanceVulcanos");
            Integer avanceFerengis = (Integer) jobDataMap.get("avanceFerengis");
            Integer avanceBetasoides = (Integer) jobDataMap.get("avanceBetasoides");
            Integer distanceVulcanos = (Integer) jobDataMap.get("distanceVulcanos");
            Integer distanceFerengis = (Integer) jobDataMap.get("distanceFerengis");
            Integer distanceBetasoides = (Integer) jobDataMap.get("distanceBetasoides");
            Boolean logData = (Boolean) jobDataMap.get("logData");


            processor.calculate(initVulcanos, initFerengis, initBetasoides, year, avanceVulcanos,
                    avanceFerengis, avanceBetasoides, distanceVulcanos, distanceFerengis, distanceBetasoides,logData!=null?logData:false);
            Long end= new Date().getTime();

            logger.info("************Finish in "+(end-start)/1000+" Seconds)*******************");
        } catch (JobExecutionException e) {
            logger.error("Error Executing " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error Executing " + e.getMessage(), e);
            throw new JobExecutionException(e);

        }
    }




}
