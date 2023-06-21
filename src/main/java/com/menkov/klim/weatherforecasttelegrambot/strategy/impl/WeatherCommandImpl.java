package com.menkov.klim.weatherforecasttelegrambot.strategy.impl;

import com.menkov.klim.weatherforecasttelegrambot.entity.User;
import com.menkov.klim.weatherforecasttelegrambot.entity.WeatherData;
import com.menkov.klim.weatherforecasttelegrambot.service.UserService;
import com.menkov.klim.weatherforecasttelegrambot.service.WeatherService;
import com.menkov.klim.weatherforecasttelegrambot.strategy.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class WeatherCommandImpl implements Command {

    WeatherService weatherService;
    UserService userService;

    @Override
    public void execute(Update update, SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        User user = userService.getUserByChatId(chatId);
        WeatherData weatherData = weatherService.getWeather(user.getCity());

        message.setText("\uD83D\uDCCD Weather in " + weatherData.getName() + ", " + weatherData.getSysData().getCountry() + ":\n" +
                "\uD83D\uDE0E Description: " + weatherData.getWeatherDescriptions().get(0).getDescription() + "\n" +
                "\uD83C\uDF21 Temperature: " + weatherData.getMainData().getTemp() + "°C\n" +
                "\uD83C\uDF27 Humidity: " + weatherData.getMainData().getHumidity() + "%\n" +
                "\uD83D\uDCA8 Wind Speed: " + weatherData.getWindData().getSpeed() + " m/s\n" +
                "☀\uFE0F Temperature today is from " + weatherData.getMainData().getTempMin() + "°C to "
                + weatherData.getMainData().getTempMax() + "°C\n" +
                "♨\uFE0F Pressure is: " + weatherData.getMainData().getPressure() + "hPa");
    }
}
