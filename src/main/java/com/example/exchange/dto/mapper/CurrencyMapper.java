package com.example.exchange.dto.mapper;

import com.example.exchange.dto.CurrencyDto;
import com.example.exchange.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements Mapper<CurrencyDto, Currency> {
    @Override
    public CurrencyDto mapToDto(Currency currency) {
        return new CurrencyDto(currency.getCode());
    }
}
