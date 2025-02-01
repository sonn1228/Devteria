package com.sonnguyen.base.exception;


import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
