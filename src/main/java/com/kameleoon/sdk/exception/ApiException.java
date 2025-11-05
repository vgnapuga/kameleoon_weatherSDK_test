package com.kameleoon.sdk.exception;


public class ApiException extends WeatherClientException {

    private static final String MESSAGE = "API request failed";

    public ApiException() {
        super(MESSAGE);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
