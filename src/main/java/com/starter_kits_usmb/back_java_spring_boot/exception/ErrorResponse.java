package com.starter_kits_usmb.back_java_spring_boot.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String message;
}
