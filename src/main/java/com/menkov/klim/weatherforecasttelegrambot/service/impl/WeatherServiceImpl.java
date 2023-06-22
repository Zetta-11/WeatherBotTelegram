package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherForecast;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.map.apiKey}")
    private String apiKey;

    @Override
    public WeatherData getWeather(String location) throws HttpClientErrorException {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);

        return weatherData;
    }

    @Override
    public WeatherForecast getWeatherForecast(String location) throws HttpClientErrorException {
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + location + "&units=metric&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        WeatherForecast weatherForecast = restTemplate.getForObject(apiUrl, WeatherForecast.class);

        return weatherForecast;
    }

    @Override
    public boolean cityIsValid(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey;

        try {
            RestTemplate restTemplate = new RestTemplate();
            WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);

            return true;
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    @Override
    public String createWeatherMessage(WeatherData weatherData) {
        return "\uD83D\uDCCD Weather in " + weatherData.getName() + ", " + weatherData.getSysData().getCountry() + ":\n" +
                "\uD83D\uDE0E Description: " + weatherData.getWeatherDescriptions().get(0).getDescription() + "\n" +
                "\uD83C\uDF21 Temperature: " + weatherData.getMainData().getTemp() + "°C\n" +
                "\uD83C\uDF27 Humidity: " + weatherData.getMainData().getHumidity() + "%\n" +
                "\uD83D\uDCA8 Wind Speed: " + weatherData.getWindData().getSpeed() + " m/s\n" +
                "☀\uFE0F Temperature today is from " + weatherData.getMainData().getTempMin() + "°C to "
                + weatherData.getMainData().getTempMax() + "°C\n" +
                "♨\uFE0F Pressure is: " + weatherData.getMainData().getPressure() + "hPa";
    }


}
