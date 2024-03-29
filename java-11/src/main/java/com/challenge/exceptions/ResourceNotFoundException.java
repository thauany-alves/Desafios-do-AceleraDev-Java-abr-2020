package com.challenge.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName){
        super("Resource: " + resourceName + " not found");
    }

}