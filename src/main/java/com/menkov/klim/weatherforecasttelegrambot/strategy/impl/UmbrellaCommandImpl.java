package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class UmbrellaCommandImpl implements Command {

    private BotService botService;

    private WeatherService weatherService;

    private UserService userService;

    @Override
    public void execute(Update update, SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);
        WeatherData weatherData = weatherService.getWeather(user.getCity());
        String currentWeather = weatherData.getWeatherDescriptions().get(0).getDescription();

        if (currentWeather.contains("rain") || currentWeather.contains("drizzle") || currentWeather.contains("storm")) {
            message.setText(botService.getTAKE_UMBRELLA_MESSAGE());
        } else {
            message.setText(botService.getNO_UMBRELLA_MESSAGE());
        }
    }
}
