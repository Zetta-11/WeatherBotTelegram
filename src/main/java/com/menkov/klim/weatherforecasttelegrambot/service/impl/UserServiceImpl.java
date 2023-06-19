package com.menkov.klim.weatherforecasttelegrambot.service.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.repository.UserRepository;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByChatId(long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
