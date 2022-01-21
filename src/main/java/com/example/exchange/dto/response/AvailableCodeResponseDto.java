package com.example.exchange.dto.response;

import com.example.exchange.model.Currency;
import java.util.ArrayList;
import java.util.List;

public class AvailableCodeResponseDto {
    public final List<String> availableCode = new ArrayList<>();

    public void addCodes(List<Currency> allCurrencies) {
        allCurrencies.forEach(currency -> availableCode.add(currency.getCode()));
    }
}
