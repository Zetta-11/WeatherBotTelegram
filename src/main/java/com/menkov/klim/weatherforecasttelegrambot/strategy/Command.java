package com.menkov.klim.weatherforecasttelegrambot.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void execute(Update update, SendMessage message);
}
