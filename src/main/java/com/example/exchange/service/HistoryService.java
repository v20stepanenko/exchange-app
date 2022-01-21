package com.example.exchange.service;

import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.dao.criteria.HistoryDAOCriteria;
import com.example.exchange.dto.HistoryDto;
import com.example.exchange.dto.mapper.HistoryMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    HistoryDAO historyDAO;
    HistoryDAOCriteria historyDAOCriteria;
    HistoryMapper historyMapper;

    public HistoryService(HistoryDAO historyDAO, HistoryDAOCriteria historyDAOCriteria, HistoryMapper historyMapper) {
        this.historyDAO = historyDAO;
        this.historyDAOCriteria = historyDAOCriteria;
        this.historyMapper = historyMapper;
    }

    public List<HistoryDto> getByRequestParam(Map<String, String> allParams) {
        return historyDAOCriteria.findHistoryByRequestParam(allParams).stream()
                .map(historyMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
