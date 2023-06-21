package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherForecast;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@AllArgsConstructor
public class WeatherForecastCommandImpl implements Command {

    WeatherService weatherService;
    UserService userService;

    @Override
    public void execute(Update update, SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        WeatherForecast weatherForecast = weatherService.getWeatherForecast(userService.getUserByChatId(chatId).getCity());
        StringBuilder forecastMessage = new StringBuilder();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < weatherForecast.getList().size(); i += 8) {
            WeatherForecast.WeatherForecastItem forecastItem = weatherForecast.getList().get(i);
            LocalDateTime forecastDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(forecastItem.getDt()), ZoneId.systemDefault());
            LocalDate forecastDate = forecastDateTime.toLocalDate();

            if (forecastDate.isAfter(currentDate)) {
                forecastMessage.append("\n\uD83D\uDCCD Date: ").append(forecastDate);
                forecastMessage.append("\n\uD83C\uDF21 Temp: ").append(forecastItem.getMain().getTemp()).append("°C");
                forecastMessage.append("\n\uD83D\uDE0E Description: ").append(forecastItem.getWeather().get(0).getDescription());
                forecastMessage.append("\n-------------------------------------");
            }
        }
        message.setText(forecastMessage.toString());
    }
}
