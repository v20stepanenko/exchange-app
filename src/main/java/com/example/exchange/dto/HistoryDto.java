package com.example.exchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryDto {
    public final String currencyFromCode;
    public final String currencyToCode;
    public final BigDecimal rate;
    public final LocalDateTime time;

    public HistoryDto(String currencyFromCode, String currencyToCode, BigDecimal coefficient, LocalDateTime time) {
        this.currencyFromCode = currencyFromCode;
        this.currencyToCode = currencyToCode;
        this.rate = coefficient;
        this.time = time;
    }
}
