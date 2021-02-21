package com.prueba.meli.error;

import com.prueba.meli.controller.WeatherController;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({Exception.class,NullPointerException.class})
    public final ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
        logger.error("Error "+ex.getMessage(),ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Error desconocido", details,HttpStatus.INTERNAL_SERVER_ERROR,request.getContextPath());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SchedulerException.class)
    public final ResponseEntity<Object> handleSchedulerException(final Exception ex, final WebRequest request) {
        logger.error("Error "+ex.getMessage(),ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Error al agendar la tarea", details,HttpStatus.INTERNAL_SERVER_ERROR,request.getContextPath());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(final Exception ex, final WebRequest request) {
        logger.error("Error "+ex.getMessage(),ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details,HttpStatus.NOT_FOUND,request.getContextPath());
        return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DateOutOfBoundException.class)
    public final ResponseEntity<Object> hanfleDateOutOfBoundException(final Exception ex, final WebRequest request) {
        logger.error("Error "+ex.getMessage(),ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details,HttpStatus.BAD_REQUEST,request.getContextPath());
        return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequests(final Exception ex,final WebRequest request) {
        logger.error("Error "+ex.getMessage(),ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse("Parámetros incorrectos",new ArrayList<>(),HttpStatus.BAD_REQUEST,request.getContextPath());
        return new ResponseEntity<Object>(error, new HttpHeaders(), status);
    }

}
