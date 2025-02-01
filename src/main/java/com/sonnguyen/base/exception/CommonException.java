package com.sonnguyen.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;
}
