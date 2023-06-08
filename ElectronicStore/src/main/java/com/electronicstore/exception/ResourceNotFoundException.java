package com.electronicstore.exception;

import com.electronicstore.Config.AppConstatnt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException() {
        super(AppConstatnt.EXCEPTION_MESSAGE);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
