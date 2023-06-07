package com.electronicstore.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException() {
        super("Resource Not Found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
