package com.skibnev.telegrambot.newstelegrambot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:application.properties")
@Component
public class Properties {

    @Autowired
    private WeatherApiConfig weatherApiConfig;
    @Autowired
    private CurrencyExchangeConfig currencyExchangeConfig;
    @Autowired
    private NewsRssChannelConfig newsRssChannelConfig;

    @Data
    @Component
    public static class WeatherApiConfig {
        @Value("${weather.api.base.url}")
        private String baseUrl;
        @Value("${weather.api.access.key}")
        private String accessKey;
    }

    @Data
    @Component
    public static class CurrencyExchangeConfig {
        @Value("${currency.exchange.base.url}")
        private String baseUrl;
        @Value("${currency.exchange.access.key}")
        private String accessKey;
    }

    @Data
    @Component
    public static class NewsRssChannelConfig {
        @Value("${rss.channel.url}")
        private String channelURL;
    }
}
