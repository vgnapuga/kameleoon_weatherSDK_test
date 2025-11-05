package com.kameleoon.sdk.exception;


public class ApiInvalidKeyException extends ApiException {

    private static final String MESSAGE_TEMPLATE = "Invalid API key: %s";

    private final String apiKey;

    public ApiInvalidKeyException(String apiKey) {
        super(String.format(MESSAGE_TEMPLATE, apiKey));
        this.apiKey = apiKey;
    }

    public ApiInvalidKeyException(String apiKey, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, apiKey), cause);
        this.apiKey = apiKey;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

}
