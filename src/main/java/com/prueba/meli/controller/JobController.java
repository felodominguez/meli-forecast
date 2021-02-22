package com.prueba.meli.controller;


import com.prueba.meli.business.Processor;
import com.prueba.meli.error.DateOutOfBoundException;
import com.prueba.meli.error.ErrorResponse;
import com.prueba.meli.error.RecordNotFoundException;
import com.prueba.meli.job.ProcessJob;
import com.prueba.meli.web.*;
import com.prueba.meli.to.DayTO;
import io.swagger.annotations.*;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/scheduler")
@Api(value = "Controlador de predicciones", description = "Controlador para la generación de predicciones", produces = "application/json")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private Processor processor;

    @Autowired
    private ProcessJob processorJob;



    @ApiOperation(notes = "Servicio para agendar una tarea",produces = "application/json", value = "Servicio para agendar la tarea de predicción")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = AddSchedulerTaskResponse.class),
            @ApiResponse(code = 400, message = "Parámetros inválidos", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error general", response = ErrorResponse.class)
    })
    @PostMapping("/schedule")
    public ResponseEntity<AddSchedulerTaskResponse> scheduleTask(
            @ApiParam(name = "Parámetros de agenda y predicciones", value = "Parámetros para la calendarización de las predicciones", required = true)
            @Valid @RequestBody SchedulerTaskRequest scheduleTaskRequest) throws SchedulerException {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleTaskRequest.getDateTime(), scheduleTaskRequest.getTimeZone());
            if (dateTime.isBefore(ZonedDateTime.now())) {
                throw new DateOutOfBoundException("La fecha y hora debe ser posterior a la actual");
            }



            JobDetail jobDetail = buildJobDetail(scheduleTaskRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);


            AddSchedulerTaskResponse addSchedulerTaskResponse = new AddSchedulerTaskResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Tarea agendada con éxito!");
            return ResponseEntity.ok(addSchedulerTaskResponse);

        } catch (SchedulerException ex) {
            logger.error("Error agendando tarea. " + ex.getMessage(), ex);
            throw ex;
        }

    }

    @PostMapping("/now")
    @ApiOperation(notes = "Servicio para ejecutar la tarea de predicciones", value = "Servicio que ejecuta la generación de predicciones",produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = TaskResponse.class),
            @ApiResponse(code = 400, message = "Parámetros inválidos", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error general", response = ErrorResponse.class)
    })
    public ResponseEntity<TaskResponse> now(
            @ApiParam(name = "Parámetros de predicciones", value = "Parámetros para la generación de las predicciones", required = true)
            @Valid @RequestBody TaskRequest generalTaskRequest) throws Exception {
        Integer result = processor.calculate(generalTaskRequest.getInitVulcanos(), generalTaskRequest.getInitFerengis(), generalTaskRequest.getInitBetasoides(), generalTaskRequest.getYear(), generalTaskRequest.getAvanceVulcanos(), generalTaskRequest.getAvanceFerengis(), generalTaskRequest.getAvanceBetasoides(), generalTaskRequest.getDistanceVulcanos(), generalTaskRequest.getDistanceFerengis(), generalTaskRequest.getDistanceBetasoides(), generalTaskRequest.getLogData());
        TaskResponse response = new TaskResponse(true,"Se generaron "+result+" predicciones correctamente.");
      return ResponseEntity.ok(response);
    }





    private JobDetail buildJobDetail(GeneralTaskRequest scheduleRequest) {
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
        jobDataMap.put("logData", scheduleRequest.getLogData());

        return JobBuilder.newJob(ProcessJob.class)
                .withIdentity(UUID.randomUUID().toString(), "task-jobs")
                .withDescription("Generación de predicciones")
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

