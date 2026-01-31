package com.yarrd.Exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Invalid input detected.");  
    }

    public InvalidInputException(String str) {
        super("Invalid input detected: " + str);  
    }
}
