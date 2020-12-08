package com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi;

import com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi.dto.WeatherApiResponseDTO;
import org.springframework.stereotype.Controller;

@Controller
public class WeatherApiConvertor {

    public String convert(WeatherApiResponseDTO responseDTO) {
        return System.lineSeparator() + " Temperature: " + responseDTO.getCurrent().getTemp_c() + " Wind: " + responseDTO.getCurrent().getWind_mph()
               + System.lineSeparator() + " Wind direction: " + responseDTO.getCurrent().getWind_dir() + " Pressure: " + responseDTO.getCurrent().getPressure_mb()
               + System.lineSeparator() + " Humidity: " + responseDTO.getCurrent().getHumidity();
    }
}
