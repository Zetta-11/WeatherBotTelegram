package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class WeatherCommandImpl implements Command {

    WeatherService weatherService;

    UserService userService;

    @Override
    public void execute(Update update, SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);
        WeatherData weatherData = weatherService.getWeather(user.getCity());

        message.setText("Weather in " + weatherData.getName() + ", " + weatherData.getSysData().getCountry() + ":\n" +
                "Description: " + weatherData.getWeatherDescriptions()[0].getDescription() + "\n" +
                "Temperature: " + weatherData.getMainData().getTemp() + "°C\n" +
                "Humidity: " + weatherData.getMainData().getHumidity() + "%\n" +
                "Wind Speed: " + weatherData.getWindData().getSpeed() + " m/s");
    }
}
