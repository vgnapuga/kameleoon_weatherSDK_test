package com.kameleoon.sdk.exception;


public class WeatherClientException extends Exception {

    public WeatherClientException(String message) {
        super(message);
    }

    public WeatherClientException(Throwable cause) {
        super(cause);
    }

    public WeatherClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
