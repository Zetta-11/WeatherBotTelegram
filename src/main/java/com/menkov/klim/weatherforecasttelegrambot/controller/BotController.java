package com.menkov.klim.weatherforecasttelegrambot.controller;

import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotController extends TelegramLongPollingBot {

    @lombok.Getter
    @Value("${telegram.bot.username}")
    private String botUsername;

    @lombok.Getter
    @Value("${telegram.bot.token}")
    private String botToken;

    @Autowired
    private WeatherService weatherService;

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String city = update.getMessage().getText();

        try {
            WeatherData weatherData = weatherService.getWeather(city);

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Weather in " + weatherData.getName() + ", " + weatherData.getSysData().getCountry() + ":\n" +
                    "Description: " + weatherData.getWeatherDescriptions()[0].getDescription() + "\n" +
                    "Temperature: " + weatherData.getMainData().getTemp() + "°C\n" +
                    "Humidity: " + weatherData.getMainData().getHumidity() + "%\n" +
                    "Wind Speed: " + weatherData.getWindData().getSpeed() + " m/s");
            execute(message);
        } catch (Exception ex) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Invalid City! Try again");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
