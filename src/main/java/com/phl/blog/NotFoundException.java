package com.phl.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Penghanlin-verysix
 * @create 2020-03-30-17:55
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(){

    }

    public NotFoundException(String message){
         super(message);
    }

    public NotFoundException(String message,Throwable cause){
        super(message,cause);
    }

}
