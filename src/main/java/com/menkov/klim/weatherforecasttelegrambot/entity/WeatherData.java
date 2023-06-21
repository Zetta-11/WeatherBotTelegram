package com.menkov.klim.weatherforecasttelegrambot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private String name;

    @JsonAlias("weather")
    private List<WeatherDescription> weatherDescriptions;

    @JsonAlias("main")
    private MainData mainData;

    @JsonAlias("wind")
    private WindData windData;

    @JsonAlias("clouds")
    private CloudData cloudData;

    @JsonAlias("sys")
    private SysData sysData;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherDescription {
        private String main;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MainData {
        private double temp;

        @JsonAlias("feels_like")
        private double feelsLike;

        @JsonAlias("temp_min")
        private double tempMin;

        @JsonAlias("temp_max")
        private double tempMax;

        private int pressure;
        private int humidity;

        @JsonAlias("sea_level")
        private int seaLevel;

        @JsonAlias("grnd_level")
        private int groundLevel;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WindData {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CloudData {
        private int all;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SysData {
        private String country;
        private long sunrise;
        private long sunset;
    }
}