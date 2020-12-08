package com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange;

import com.skibnev.telegrambot.newstelegrambot.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService implements ServiceHelper {

    private CurrencyExchangeAPI currencyExchangeAPI;
    private CurrencyExchangeConvertor currencyExchangeConvertor;

    @Autowired
    public CurrencyExchangeService(CurrencyExchangeAPI currencyExchangeAPI, CurrencyExchangeConvertor currencyExchangeConvertor) {
        this.currencyExchangeAPI = currencyExchangeAPI;
        this.currencyExchangeConvertor = currencyExchangeConvertor;
    }

    @Override
    public String getInfo(String origin) {
        return currencyExchangeConvertor.convert(currencyExchangeAPI.getRate());
    }
}
