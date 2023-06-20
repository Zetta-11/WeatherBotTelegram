package com.menkov.klim.weatherforecasttelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherData {
    private String name;

    private String weatherDescription;

    private double temperature;

    private double humidity;

    private double windSpeed;
}
