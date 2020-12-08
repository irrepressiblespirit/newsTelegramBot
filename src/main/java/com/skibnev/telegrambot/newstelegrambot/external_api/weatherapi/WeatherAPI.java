package com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi;

import com.skibnev.telegrambot.newstelegrambot.config.Properties;
import com.skibnev.telegrambot.newstelegrambot.external_api.weatherapi.dto.WeatherApiResponseDTO;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Headers("Content-Type: application/json")
public interface WeatherAPI {

    @RequestLine("GET /current.json?key={key}&q={origin}")
    WeatherApiResponseDTO getCurrentWeather(@Param("key") String key, @Param("origin") String origin);

    @Service
    class WeatherAPIImpl implements WeatherAPI {

        private static final JacksonEncoder jacksonEncoder = new JacksonEncoder();
        private static final JacksonDecoder jacksonDecoder = new JacksonDecoder();
        private static final int connectTimeoutMillis = 30000;
        private static final int readTimeoutMillis = 30000;
        private static final int TIMEOUT = 30;
        private final WeatherAPI feign;

        @Autowired
        public WeatherAPIImpl(Properties properties) {
            Properties.WeatherApiConfig weatherApiConfig = properties.getWeatherApiConfig();
            feign = Feign.builder()
                    .encoder(jacksonEncoder)
                    .decoder(jacksonDecoder)
                    .options(new feign.Request.Options(connectTimeoutMillis, readTimeoutMillis))
                    .retryer(Retryer.NEVER_RETRY)
                    .client(getClient())
                    .retryer(Retryer.NEVER_RETRY)
                    .target(WeatherAPI.class, weatherApiConfig.getBaseUrl());
        }

        private Client getClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS);
            return new feign.okhttp.OkHttpClient(builder.build());
        }

        @Override
        public WeatherApiResponseDTO getCurrentWeather(String key, String origin) {
            return feign.getCurrentWeather(key, origin);
        }
    }
}
