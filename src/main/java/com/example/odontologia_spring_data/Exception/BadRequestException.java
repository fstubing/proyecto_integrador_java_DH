package com.example.odontologia_spring_data.Exception;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
