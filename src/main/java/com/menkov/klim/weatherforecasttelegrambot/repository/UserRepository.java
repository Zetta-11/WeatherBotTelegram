package com.menkov.klim.weatherforecasttelegrambot.repository;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByChatId(long chatId);
}
