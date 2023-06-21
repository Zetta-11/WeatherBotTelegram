package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
@AllArgsConstructor
public class CityCommandImpl implements Command {

    private BotService botService;
    private final UserService userService;
    private final WeatherService weatherService;

    @Override
    public void execute(Update update, SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);

        if (user != null) {
            if (user.getState().equals("WAITING_FOR_CITY")) {
                String city = update.getMessage().getText();

                if (weatherService.cityIsValid(city)) {
                    user.setCity(city);
                    userService.saveUser(user);
                    message.setText(botService.getCONFIRM_MESSAGE());
                } else {
                    message.setText(botService.getWARNING_MESSAGE());
                    List<KeyboardRow> keys = botService.getWeatherButtons();
                    message.setReplyMarkup(botService.createKeyboard(keys));
                }

                user.setState("");
                userService.saveUser(user);
            } else {
                user.setState("WAITING_FOR_CITY");
                userService.saveUser(user);

                message.setText("Please enter your city:");
            }
        }
    }
}
