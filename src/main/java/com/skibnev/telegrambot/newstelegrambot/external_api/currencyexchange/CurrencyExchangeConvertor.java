package com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange;

import com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange.dto.CurrencyExchangeResponseDTO;
import org.springframework.stereotype.Controller;

@Controller
public class CurrencyExchangeConvertor {

    public String convert(CurrencyExchangeResponseDTO responseDTO) {
        return System.lineSeparator() + " EUR to UAH bid: " + responseDTO.getEur().getBid() + "EUR to UAH ask: " + responseDTO.getEur().getAsk()
                + System.lineSeparator() + " GBP to UAH bid: " + responseDTO.getGbp().getBid() + " GBP to UAH ask: " + responseDTO.getGbp().getAsk()
                + System.lineSeparator() + " USD to UAH bid: " + responseDTO.getUsd().getBid() + " USD to UAH ask: " + responseDTO.getUsd().getAsk()
                + System.lineSeparator() + " RUB to UAH bid: " + responseDTO.getRub().getBid() + " RUB to UAH ask: " + responseDTO.getRub().getAsk();
    }
}
