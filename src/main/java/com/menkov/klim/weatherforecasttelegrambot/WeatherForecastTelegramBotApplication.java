package com.menkov.klim.weatherforecasttelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.menkov.klim.weatherforecasttelegrambot",
        "org.telegram.telegrambots",
        "com.menkov.klim.weatherforecasttelegrambot.strategy"
})
public class WeatherForecastTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastTelegramBotApplication.class, args);
    }
}
