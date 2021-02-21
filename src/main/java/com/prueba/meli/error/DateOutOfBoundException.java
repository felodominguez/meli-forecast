package com.prueba.meli.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateOutOfBoundException extends RuntimeException
{
    public DateOutOfBoundException(String exception) {
        super(exception);
    }
}
