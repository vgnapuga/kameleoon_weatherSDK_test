package com.kameleoon.sdk.model;


import com.kameleoon.sdk.model.element.Sys;
import com.kameleoon.sdk.model.element.Temperature;
import com.kameleoon.sdk.model.element.Weather;
import com.kameleoon.sdk.model.element.Wind;


public final record WeatherResponse(
        Weather weather,
        Temperature temperature,
        int visibility,
        Wind wind,
        long dateTime,
        Sys sys,
        int timeZone,
        String name) {
}
