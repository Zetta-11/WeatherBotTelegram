package com.menkov.klim.weatherforecasttelegrambot.service;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;

public interface UserService {
    User getUserByChatId(long chatId);

    void saveUser(User user);
}
