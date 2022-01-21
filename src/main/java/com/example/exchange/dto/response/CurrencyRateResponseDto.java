package com.example.exchange.dto.response;

import java.math.BigDecimal;

public class CurrencyRateResponseDto {
    public final String currencyFrom;
    public final String currencyTo;
    public final BigDecimal rate;

    public CurrencyRateResponseDto(String currencyFrom, String currencyTo, BigDecimal rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
}
