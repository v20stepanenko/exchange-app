package com.example.exchange.dto.mapper;

import com.example.exchange.dto.CurrencyDto;
import com.example.exchange.dto.HistoryDto;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryMapperTest {

    @Test
    public void testMapFromEntityToModel(){
        HistoryMapper historyMapper = new HistoryMapper();
        Currency currencyFrom = new Currency("USD");
        Currency currencyTo = new Currency("UAH");
        BigDecimal rate = BigDecimal.valueOf(10);
        LocalDateTime time = LocalDateTime.now();
        History history = new History(currencyFrom, currencyTo, rate, time);
        HistoryDto expected = new HistoryDto(currencyFrom.getCode(),
                currencyTo.getCode(),
                rate,
                time);
        HistoryDto actual = historyMapper.mapToDto(history);
        assertEquals(expected, actual);
    }

}