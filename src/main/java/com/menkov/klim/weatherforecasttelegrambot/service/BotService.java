package com.menkov.klim.weatherforecasttelegrambot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public interface BotService {
    String ABOUT_MESSAGE = "Welcome to WeatherBruh! 🌤\n\n" +
            "Stay updated with the latest weather forecasts and conditions with WeatherBruh, " +
            "your personal weather companion. Get real-time weather information for any " +
            "location and receive accurate forecasts right at your fingertips.\n\nFeatures:\n🌡" +
            " Current weather conditions\n☀️ Daily forecasts\n🌧 Precipitation and humidity details\n💨 " +
            "Wind speed and direction\n📍 Customizable locations";

    String HELP_MESSAGE = "Hello!\n\nList of commands:\n/help - help";

    String START_MESSAGE = "Hello!\n Just select some commands below to know more about me =)";

    String WEATHER_MESSAGE = "Select some options below!";

    List<KeyboardRow> getMainButtons();

    List<KeyboardRow> getWeatherButtons();

    ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> buttons);

    String getABOUT_MESSAGE();

    String getHELP_MESSAGE();

    String getSTART_MESSAGE();

    String getWEATHER_MESSAGE();
}
