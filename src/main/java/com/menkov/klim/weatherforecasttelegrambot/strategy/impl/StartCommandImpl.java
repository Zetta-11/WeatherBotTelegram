package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.service.BotService;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
@AllArgsConstructor
public class StartCommandImpl implements Command {

    private final BotService botService;
    private final UserService userService;

    @Override
    public void execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);

        if (user == null) {
            user = new User();
            user.setChatId(chatId);
            user.setCity("Kyiv");
            user.setState("");
            userService.saveUser(user);
        }

        message.setText(botService.getSTART_MESSAGE());
        List<KeyboardRow> keys = botService.getMainButtons();
        message.setReplyMarkup(botService.createKeyboard(keys));
    }
}
