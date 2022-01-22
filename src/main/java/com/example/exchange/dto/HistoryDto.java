package com.example.exchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDto that = (HistoryDto) o;
        return Objects.equals(currencyFromCode, that.currencyFromCode) && Objects.equals(currencyToCode, that.currencyToCode) && Objects.equals(rate, that.rate) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyFromCode, currencyToCode, rate, time);
    }
}
