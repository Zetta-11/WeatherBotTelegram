package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class StartCommandImpl implements Command {

    @Autowired
    BotService botService;

    @Override
    public void execute(SendMessage message) {
        message.setText(botService.getSTART_MESSAGE());
        message.setReplyMarkup(botService.createKeyboard(botService.getMainButtons()));
    }
}
