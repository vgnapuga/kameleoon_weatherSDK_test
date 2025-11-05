package com.kameleoon.sdk.exception;


public class ApiCityNotFoundException extends ApiException {

    private static final String MESSAGE_TEMPLATE = "City not found: %s";

    private final String cityName;

    public ApiCityNotFoundException(String cityName) {
        super(String.format(MESSAGE_TEMPLATE, cityName));
        this.cityName = cityName;
    }

    public ApiCityNotFoundException(String cityName, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, cityName), cause);
        this.cityName = cityName;
    }

    public final String getCityName() {
        return this.cityName;
    }

}
