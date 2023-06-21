package com.menkov.klim.weatherforecasttelegrambot.controller;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import com.menkov.klim.weatherforecasttelegrambot.strategy.impl.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Component
public class BotController extends TelegramLongPollingBot {

    private Map<String, Command> commandStrategies;

    @lombok.Getter
    @Value("${telegram.bot.username}")
    private String botUsername;

    @lombok.Getter
    @Value("${telegram.bot.token}")
    private String botToken;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    UserService userService;

    @Autowired
    private BotService botService;

    @PostConstruct
    public void init() {
        commandStrategies = new HashMap<>();
        commandStrategies.put("/start", new StartCommandImpl(botService, userService));
        commandStrategies.put("Back", new StartCommandImpl(botService, userService));
        commandStrategies.put("About", new AboutCommandImpl(botService));
        commandStrategies.put("Help", new HelpCommandImpl(botService));
        commandStrategies.put("Weather", new WeatherOptionsCommandImpl(botService));
        commandStrategies.put("Do I need an umbrella today?", new UmbrellaCommandImpl(botService, weatherService, userService));
        commandStrategies.put("Set/Change City", new CityCommandImpl(botService, userService, weatherService));
        commandStrategies.put("Get weather for my City", new WeatherCommandImpl(weatherService, userService));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();

            SendMessage message = new SendMessage();
            message.setChatId(chatId);

            Command strategy = commandStrategies.getOrDefault(messageText, new UnknownCommandImpl());
            strategy.execute(message);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
