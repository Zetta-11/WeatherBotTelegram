package com.menkov.klim.weatherforecasttelegrambot.controller;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import com.menkov.klim.weatherforecasttelegrambot.strategy.impl.AboutCommandImpl;
import com.menkov.klim.weatherforecasttelegrambot.strategy.impl.HelpCommandImpl;
import com.menkov.klim.weatherforecasttelegrambot.strategy.impl.StartCommandImpl;
import com.menkov.klim.weatherforecasttelegrambot.strategy.impl.UnknownCommandImpl;
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
    private BotService botService;

    @PostConstruct
    public void init() {
        commandStrategies = new HashMap<>();
        commandStrategies.put("/start", new StartCommandImpl(botService));
        commandStrategies.put("About", new AboutCommandImpl(botService));
        commandStrategies.put("Help", new HelpCommandImpl(botService));
        //TODO
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
