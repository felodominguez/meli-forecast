package com.prueba.meli.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


public class ErrorResponse implements Serializable{


    //Mensaje de error
    private String message;

    //Listado específico de errores
    private List<String> details;

    //Codigo Http
    private HttpStatus status;

    //Uri de la invocación
    @JsonProperty("uri")
    private String uriRequested;

    public ErrorResponse(String message, List<String> details, HttpStatus status,String uriRequested) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.uriRequested=uriRequested;
    }

    //Getter and setters


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getUriRequested() {
        return uriRequested;
    }

    public void setUriRequested(String uriRequested) {
        this.uriRequested = uriRequested;
    }
}