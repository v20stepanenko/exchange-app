package com.example.exchange.dto.response;

import java.math.BigDecimal;

public class CurrencyRateResponseDto {
    public String currencyFrom;
    public String currencyTo;
    public BigDecimal rate;

    public CurrencyRateResponseDto(String currencyFrom, String currencyTo, BigDecimal rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
}
