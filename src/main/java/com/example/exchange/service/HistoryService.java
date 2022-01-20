package com.example.exchange.service;

import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.dao.criteria.HistoryDAOCriteria;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    HistoryDAO historyDAO;
    HistoryDAOCriteria historyDAOCriteria;

    public HistoryService(HistoryDAO historyDAO, HistoryDAOCriteria historyDAOCriteria) {
        this.historyDAO = historyDAO;
        this.historyDAOCriteria = historyDAOCriteria;
    }

    public void getByRequestParam(Map<String, String> allParams) {
        historyDAOCriteria.findHistoryByRequestParam(allParams);
    }
}
