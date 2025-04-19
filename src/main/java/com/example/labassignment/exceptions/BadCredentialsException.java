package com.example.labassignment.exceptions;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(){
        super("Bad credentials!");
    }
}
