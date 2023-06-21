package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class CityCommandImpl implements Command {

    private BotService botService;
    private final UserService userService;
    private final WeatherService weatherService;

    @Override
    public void execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);

        if (user != null) {
            if (user.getState().equals("WAITING_FOR_CITY")) {
                String city = message.getText();

                if (weatherService.cityIsValid(city)) {
                    user.setCity(city);
                    userService.saveUser(user);
                    message.setText(botService.getCONFIRM_MESSAGE());
                } else {
                    message.setText(botService.getWARNING_MESSAGE());
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
