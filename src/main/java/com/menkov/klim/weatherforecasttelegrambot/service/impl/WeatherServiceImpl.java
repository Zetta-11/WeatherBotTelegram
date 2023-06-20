package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.map.apiKey}")
    private String apiKey;

    public WeatherData getWeather(String location) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, WeatherData.class);
    }
}
