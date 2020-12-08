package com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange;

import com.skibnev.telegrambot.newstelegrambot.config.Properties;
import com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange.dto.CurrencyExchangeResponseDTO;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Headers("Content-Type: application/json")
public interface CurrencyExchangeAPI {

    @RequestLine("GET")
    CurrencyExchangeResponseDTO getRate();

    @Service
    class CurrencyExchangeAPIImpl implements CurrencyExchangeAPI {
        private static final JacksonEncoder jacksonEncoder = new JacksonEncoder();
        private static final JacksonDecoder jacksonDecoder = new JacksonDecoder();
        private static final int connectTimeoutMillis = 30000;
        private static final int readTimeoutMillis = 30000;
        private static final int TIMEOUT = 30;
        private final CurrencyExchangeAPI feign;

        @Autowired
        public CurrencyExchangeAPIImpl(Properties properties) {
            Properties.CurrencyExchangeConfig currencyExchangeConfig = properties.getCurrencyExchangeConfig();
            feign = Feign.builder()
                    .encoder(jacksonEncoder)
                    .decoder(jacksonDecoder)
                    .options(new feign.Request.Options(connectTimeoutMillis, readTimeoutMillis))
                    .retryer(Retryer.NEVER_RETRY)
                    .client(getClient())
                    .target(CurrencyExchangeAPI.class, currencyExchangeConfig.getBaseUrl() + currencyExchangeConfig.getAccessKey());
        }

        private Client getClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS);
            return new feign.okhttp.OkHttpClient(builder.build());
        }

        @Override
        public CurrencyExchangeResponseDTO getRate() {
            return feign.getRate();
        }
    }
}
