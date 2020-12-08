package com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi;

import com.skibnev.telegrambot.newstelegrambot.config.Properties;
import com.skibnev.telegrambot.newstelegrambot.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiService implements ServiceHelper {

    private WeatherAPI weatherAPI;
    private WeatherApiConvertor weatherApiConvertor;
    private String accessKey;

    @Autowired
    public WeatherApiService(WeatherAPI weatherAPI, WeatherApiConvertor weatherApiConvertor, Properties properties) {
        this.weatherAPI = weatherAPI;
        this.weatherApiConvertor = weatherApiConvertor;
        this.accessKey = properties.getWeatherApiConfig().getAccessKey();
    }

    @Override
    public String getInfo(String origin) {
        return weatherApiConvertor.convert(weatherAPI.getCurrentWeather(accessKey, origin));
    }
}
