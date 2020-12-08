package com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi.dto;

import lombok.Data;

@Data
public class WeatherApiResponseDTO {
    Weather current;

    @Data
    public static class Weather {
        private float temp_c;
        private float wind_mph;
        private String wind_dir;
        private float humidity;
        private float pressure_mb;
    }
}
