package com.menkov.klim.weatherforecasttelegrambot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {
    private int cnt;
    private List<WeatherForecastItem> list;

    @Data
    public static class WeatherForecastItem {
        private long dt;
        private MainData main;
        private List<Weather> weather;
        private Wind wind;
        private String dt_txt;
    }

    @Data
    public static class MainData {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int sea_level;
        private int grnd_level;
        private int humidity;
        private double temp_kf;
    }

    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }
}
