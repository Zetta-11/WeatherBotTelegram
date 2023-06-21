package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@PropertySource("classpath:messages.properties")
public class BotServiceImpl implements BotService {

    @Value("${about.message}")
    private String ABOUT_MESSAGE;

    @Value("${help.message}")
    private String HELP_MESSAGE;

    @Value("${start.message}")
    private String START_MESSAGE;

    @Value("${weather.message}")
    private String WEATHER_MESSAGE;

    @Value("${confirm.message}")
    private String CONFIRM_MESSAGE;

    @Value("${warning.message}")
    private String WARNING_MESSAGE;

    @Value("${takeUmbrella.message}")
    private String TAKE_UMBRELLA_MESSAGE;

    @Value("${noUmbrella.message}")
    private String NO_UMBRELLA_MESSAGE;

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
        row2.add(new KeyboardButton("Get weather for my City for 5 days"));

        KeyboardRow row4 = new KeyboardRow();
        row3.add(new KeyboardButton("Do I need an umbrella today?"));

        KeyboardRow row5 = new KeyboardRow();
        row3.add(new KeyboardButton("Back"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        return keyboard;
    }

    @Override
    public ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> buttons) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(buttons);

        return keyboardMarkup;
    }
}
