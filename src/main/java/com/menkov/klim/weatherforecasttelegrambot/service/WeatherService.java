package com.menkov.klim.weatherforecasttelegrambot.service;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;

public interface WeatherService {
    WeatherData getWeather(String location);
}
