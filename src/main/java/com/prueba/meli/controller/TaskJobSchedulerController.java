package com.prueba.meli.controller;


import com.prueba.meli.business.Processor;
import com.prueba.meli.job.ProcessJob;
import com.prueba.meli.web.AddSchedulerTaskRequest;
import com.prueba.meli.web.AddSchedulerTaskResponse;
import com.prueba.meli.to.DayTO;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class TaskJobSchedulerController {

    private static final Logger logger = LoggerFactory.getLogger(TaskJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private Processor processor;

    @PostMapping("/schedule")
    public ResponseEntity<AddSchedulerTaskResponse> scheduleTask(@Valid @RequestBody AddSchedulerTaskRequest scheduleTaskRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleTaskRequest.getDateTime(), scheduleTaskRequest.getTimeZone());
            if (dateTime.isBefore(ZonedDateTime.now())) {
                AddSchedulerTaskResponse addSchedulerTaskResponse = new AddSchedulerTaskResponse(false,
                        "La fecha y hora debe ser posterior a la actual");
                return ResponseEntity.badRequest().body(addSchedulerTaskResponse);
            }

            JobDetail jobDetail = buildJobDetail(scheduleTaskRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            AddSchedulerTaskResponse addSchedulerTaskResponse = new AddSchedulerTaskResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Tarea agendada con éxito!");
            return ResponseEntity.ok(addSchedulerTaskResponse);
        } catch (SchedulerException ex) {
            logger.error("Error agendando tarea. "+ex.getMessage(), ex);

            AddSchedulerTaskResponse addSchedulerTaskResponse = new AddSchedulerTaskResponse(false,
                    "Error agendando la tarea.!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addSchedulerTaskResponse);
        }
    }

    @PostMapping("/now")
    public ResponseEntity<Map<Long, DayTO>> now(@Valid @RequestBody AddSchedulerTaskRequest scheduleTaskRequest) throws Exception {
        try {

            return ResponseEntity.ok(processor.calculate(scheduleTaskRequest.getInitVulcanos(), scheduleTaskRequest.getInitFerengis(), scheduleTaskRequest.getInitBetasoides(), scheduleTaskRequest.getYear(), scheduleTaskRequest.getAvanceVulcanos(), scheduleTaskRequest.getAvanceFerengis(), scheduleTaskRequest.getAvanceBetasoides(), scheduleTaskRequest.getDistanceVulcanos(), scheduleTaskRequest.getDistanceFerengis(), scheduleTaskRequest.getDistanceBetasoides(), scheduleTaskRequest.getLogData()));
        } catch (Exception ex) {
            logger.error("Error scheduling task", ex);
            throw ex;
        }
    }

    private JobDetail buildJobDetail(AddSchedulerTaskRequest scheduleRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("initVulcanos", scheduleRequest.getInitVulcanos());
        jobDataMap.put("initFerengis", scheduleRequest.getInitFerengis());
        jobDataMap.put("initBetasoides", scheduleRequest.getInitBetasoides());
        jobDataMap.put("year", scheduleRequest.getYear());
        jobDataMap.put("avanceVulcanos", scheduleRequest.getAvanceVulcanos());
        jobDataMap.put("avanceFerengis", scheduleRequest.getAvanceFerengis());
        jobDataMap.put("avanceBetasoides", scheduleRequest.getAvanceBetasoides());
        jobDataMap.put("distanceVulcanos", scheduleRequest.getDistanceVulcanos());
        jobDataMap.put("distanceFerengis", scheduleRequest.getDistanceFerengis());
        jobDataMap.put("distanceBetasoides", scheduleRequest.getDistanceBetasoides());
        jobDataMap.put("distanceBetasoides", scheduleRequest.getDistanceBetasoides());
        jobDataMap.put("logData", scheduleRequest.getLogData());

        return JobBuilder.newJob(ProcessJob.class)
                .withIdentity(UUID.randomUUID().toString(), "task-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "task-triggers")
                .withDescription("Process all information")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}

