package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.controller.BotController;
import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.NotificationService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private BotController botController;
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;

    @Scheduled(cron = "${cron.expression}")
    @Override
    public void sendDailyNotifications() {
        List<User> users = userService.getAllUsers();
        List<SendMessage> messages = new ArrayList<>();

        for (User user : users) {
            WeatherData weatherData = weatherService.getWeather(user.getCity());
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(user.getChatId()));
            message.setText(weatherService.createWeatherMessage(weatherData));
            messages.add(message);
        }
        botController.sendMessages(messages);
    }
}
