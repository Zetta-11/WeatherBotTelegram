package com.menkov.klim.weatherforecasttelegrambot.service;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherForecast;

public interface WeatherService {
    WeatherData getWeather(String location);

    WeatherForecast getWeatherForecast(String location);

    boolean cityIsValid(String city);
}
