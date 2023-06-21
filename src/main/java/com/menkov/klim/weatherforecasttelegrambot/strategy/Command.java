package com.menkov.klim.weatherforecasttelegrambot.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {
    void execute(SendMessage message);
}
