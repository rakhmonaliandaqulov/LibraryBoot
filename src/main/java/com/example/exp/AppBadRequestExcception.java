package com.example.exp;

public class AppBadRequestExcception extends RuntimeException{

    public AppBadRequestExcception(String message) {
        super(message);
    }
}
