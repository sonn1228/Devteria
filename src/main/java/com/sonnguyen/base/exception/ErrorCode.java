package com.sonnguyen.base.exception;


import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User existed")
    ;
    private int code;
    private String message;

    ErrorCode (int code, String message){
        this.code = code;
        this.message = message;
    }


}
