package com.techvariable.traxpense.core.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author utpal
 * com.techvariable.traxpense.core.common.exceptions
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super();
    }

    public BadRequestException(String message){ super(message); }

    public BadRequestException(String message, Throwable cause){ super(message, cause); }
}
