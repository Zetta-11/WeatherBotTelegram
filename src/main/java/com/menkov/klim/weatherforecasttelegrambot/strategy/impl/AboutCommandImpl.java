package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class AboutCommandImpl implements Command {

    private final BotService botService;

    @Override
    public void execute(SendMessage message) {
        message.setText(botService.getABOUT_MESSAGE());
    }
}
