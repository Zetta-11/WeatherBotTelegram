package com.menkov.klim.weatherforecasttelegrambot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public interface BotService {

    List<KeyboardRow> getMainButtons();

    List<KeyboardRow> getWeatherButtons();

    ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> buttons);

    String getABOUT_MESSAGE();

    String getHELP_MESSAGE();

    String getSTART_MESSAGE();

    String getWEATHER_MESSAGE();
}
