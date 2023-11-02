package com.starter_kits_usmb.back_java_spring_boot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class InvalidAuthHeaderException extends RuntimeException{
    public InvalidAuthHeaderException() {
        super("The provided token is either missing, invalid, or does not start with \"Bearer \".");
    }
}
