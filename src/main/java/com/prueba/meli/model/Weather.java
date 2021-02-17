package com.prueba.meli.model;

import java.io.Serializable;

/**
 * @author FD
 * Enumerador que representa los diferentes climas
 */
public enum Weather implements Serializable {
    SEQUIA("Sequía", 1),
    LLUVIA("Lluvia", 2),
    OPTIMO("Óptimo", 3),
    NORMAL("Normal", 4);

    Weather(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}