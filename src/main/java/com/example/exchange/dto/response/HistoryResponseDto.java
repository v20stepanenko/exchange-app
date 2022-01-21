package com.example.exchange.dto.response;

import com.example.exchange.dto.HistoryDto;
import java.util.List;

public class HistoryResponseDto {
    public List<HistoryDto> histories;

    public HistoryResponseDto(List<HistoryDto> histories) {
        this.histories = histories;
    }
}
