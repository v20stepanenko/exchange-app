package com.example.exchange.dto.mapper;

import com.example.exchange.dto.HistoryDto;
import com.example.exchange.model.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper implements Mapper<HistoryDto, History> {
    @Override
    public HistoryDto mapToDto(History history) {
        return new HistoryDto(history.getFromCurrency().getCode(),
                history.getToCurrency().getCode(),
                history.getCoefficient(),
                history.getTimeStamp());
    }
}
