package com.yarrd.Exceptions;

public class InvalidOperationException  extends Exception {
    public InvalidOperationException() {
        super("Invalid operation detected.");  
    }

    public InvalidOperationException(char m) {
        super("Invalid operation detected: " + m);  
    }
}