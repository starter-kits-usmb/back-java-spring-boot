package com.starter_kits_usmb.back_java_spring_boot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class AlreadyExistsException extends RuntimeException{
    private final String ressourceName;
    private final String fieldName;
    private final String fieldValue;

    public AlreadyExistsException(String ressourceName, String fieldName, String fieldValue) {
        super(String.format("%s Already Exist %s : %s", ressourceName, fieldName, fieldValue));
        this.ressourceName = ressourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
