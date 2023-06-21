package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
@AllArgsConstructor
public class WeatherCommandImpl implements Command {

    private BotService botService;
    @Override
    public void execute(SendMessage message) {
        message.setText(botService.getWEATHER_MESSAGE());
        List<KeyboardRow> keys = botService.getWeatherButtons();
        message.setReplyMarkup(botService.createKeyboard(keys));
    }
}
