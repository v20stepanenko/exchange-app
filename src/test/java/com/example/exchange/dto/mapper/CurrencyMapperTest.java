package com.example.exchange.dto.mapper;


import com.example.exchange.dto.CurrencyDto;
import com.example.exchange.model.Currency;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyMapperTest {

    @Test
    public void testMapFromEntityToModel(){
        CurrencyMapper currencyMapper = new CurrencyMapper();
        Currency currency = new Currency("USD");
        CurrencyDto expected = new CurrencyDto("USD");
        CurrencyDto actual = currencyMapper.mapToDto(currency);
        assertEquals(expected, actual);
    }

}