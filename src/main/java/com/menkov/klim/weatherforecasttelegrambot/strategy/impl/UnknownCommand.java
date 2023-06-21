package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UnknownCommand implements Command {
    @Override
    public void execute(SendMessage message) {
        message.setText("Unknown command!");
    }
}
