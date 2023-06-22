package com.menkov.klim.weatherforecasttelegrambot.service;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;

import java.util.List;

public interface UserService {
    User getUserByChatId(long chatId);

    List<User> getAllUsers();

    void saveUser(User user);
}
