package com.example.exchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryDto {
    private final String currencyFromCode;
    private final String currencyToCode;
    private final BigDecimal rate;
    private final LocalDateTime time;

    public HistoryDto(String currencyFromCode, String currencyToCode, BigDecimal coefficient, LocalDateTime time) {
        this.currencyFromCode = currencyFromCode;
        this.currencyToCode = currencyToCode;
        this.rate = coefficient;
        this.time = time;
    }

    public String getCurrencyFromCode() {
        return currencyFromCode;
    }

    public String getCurrencyToCode() {
        return currencyToCode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
