package com.example.exchange.dto;

import java.time.LocalDateTime;

public class HistoryDto {
    private String currencyFromCode;
    private String currencyToCode;
    private float coefficient;
    private LocalDateTime time;

    public HistoryDto(String currencyFromCode, String currencyToCode, float coefficient, LocalDateTime time) {
        this.currencyFromCode = currencyFromCode;
        this.currencyToCode = currencyToCode;
        this.coefficient = coefficient;
        this.time = time;
    }

    public String getCurrencyFromCode() {
        return currencyFromCode;
    }

    public String getCurrencyToCode() {
        return currencyToCode;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
