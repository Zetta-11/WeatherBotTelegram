package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class CityCommandImpl implements Command {

    private BotService botService;
    private final UserService userService;

    @Override
    public void execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);
        String city = message.getText();

        if (user != null) {
            user.setCity(city);
            userService.saveUser(user);
        }
        message.setText(botService.getSTART_MESSAGE());
    }
}
