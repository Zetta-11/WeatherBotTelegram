package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class BotServiceImpl implements BotService {

    @Override
    public List<KeyboardRow> getMainButtons() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Weather"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Help"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("About"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        return keyboard;
    }

    @Override
    public List<KeyboardRow> getWeatherButtons() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Set/Change City"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Get weather for my City"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Do I need an umbrella today?"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        return keyboard;
    }

    @Override
    public ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> buttons) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(buttons);

        return keyboardMarkup;
    }

    @Override
    public String getABOUT_MESSAGE() {
        return ABOUT_MESSAGE;
    }

    @Override
    public String getHELP_MESSAGE() {
        return HELP_MESSAGE;
    }

    @Override
    public String getSTART_MESSAGE() {
        return START_MESSAGE;
    }

    @Override
    public String getWEATHER_MESSAGE() {
        return WEATHER_MESSAGE;
    }
}
