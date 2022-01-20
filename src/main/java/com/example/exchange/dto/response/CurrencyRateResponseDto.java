package com.example.exchange.dto.response;

public class CurrencyRateResponseDto {
    public String currencyFrom;
    public String currencyTo;
    public float rate;

    public CurrencyRateResponseDto(String currencyFrom, String currencyTo, float rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
}
