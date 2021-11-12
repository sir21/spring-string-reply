package com.beta.replyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInput  extends  RuntimeException{
    public  InvalidInput() {
        super("Invalid input");
    }
}
