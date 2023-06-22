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
    WeatherService weatherService;

    @Scheduled(cron = "0 0 9 * * *")
    @Override
    public void sendDailyNotifications() {
        List<User> users = userService.getAllUsers();
        List<SendMessage> messages = new ArrayList<>();

        for (User user : users) {
            WeatherData weatherData = weatherService.getWeather(user.getCity());
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(user.getChatId()));
            message.setText("\uD83D\uDCCD Weather in " + weatherData.getName() + ", " + weatherData.getSysData().getCountry() + ":\n" +
                    "\uD83D\uDE0E Description: " + weatherData.getWeatherDescriptions().get(0).getDescription() + "\n" +
                    "\uD83C\uDF21 Temperature: " + weatherData.getMainData().getTemp() + "°C\n" +
                    "\uD83C\uDF27 Humidity: " + weatherData.getMainData().getHumidity() + "%\n" +
                    "\uD83D\uDCA8 Wind Speed: " + weatherData.getWindData().getSpeed() + " m/s\n" +
                    "☀\uFE0F Temperature today is from " + weatherData.getMainData().getTempMin() + "°C to "
                    + weatherData.getMainData().getTempMax() + "°C\n" +
                    "♨\uFE0F Pressure is: " + weatherData.getMainData().getPressure() + "hPa");
            messages.add(message);
        }
        botController.sendMessages(messages);
    }
}
