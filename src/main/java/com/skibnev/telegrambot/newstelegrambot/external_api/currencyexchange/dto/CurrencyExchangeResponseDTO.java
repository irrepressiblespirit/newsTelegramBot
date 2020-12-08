package com.skibnev.telegrambot.newstelegrambot.external_api.currencyexchange.dto;

import lombok.Data;

@Data
public class CurrencyExchangeResponseDTO {
    CurrencyExchangeRate eur;
    CurrencyExchangeRate gbp;
    CurrencyExchangeRate usd;
    CurrencyExchangeRate rub;
}
