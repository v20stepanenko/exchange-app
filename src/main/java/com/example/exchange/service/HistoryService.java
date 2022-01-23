package com.example.exchange.service;

import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.dao.criteria.HistoryDAOCriteria;
import com.example.exchange.dto.HistoryDto;
import com.example.exchange.dto.mapper.HistoryMapper;
import com.example.exchange.exceptions.BadParamsException;
import com.example.exchange.exceptions.CurrencyException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class HistoryService {
    HistoryDAO historyDAO;
    HistoryDAOCriteria historyDAOCriteria;
    HistoryMapper historyMapper;
    CurrencyService currencyService;

    public HistoryService(HistoryDAO historyDAO, HistoryDAOCriteria historyDAOCriteria, HistoryMapper historyMapper, CurrencyService currencyService) {
        this.historyDAO = historyDAO;
        this.historyDAOCriteria = historyDAOCriteria;
        this.historyMapper = historyMapper;
        this.currencyService = currencyService;
    }

    public List<HistoryDto> getByRequestParam(Map<String, String> allParams) {
        return historyDAOCriteria.findHistoryByRequestParam(allParams).stream()
                .map(historyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean checkParams(@RequestParam Map<String, String> allParams) { // FIXME: 24.01.2022 Add testCase
        Set<String> keys = allParams.keySet();
        Set<String> availableParams = getAvailableParams();
        keys.forEach(key -> {
            if (!availableParams.contains(key)) {
                throw new BadParamsException("Not available param -> " + key,
                        BadParamsException.Reason.BAD_PARAMS);
            }
        });
        String currencyFrom = allParams.get("currencyFrom");
        String currencyTo = allParams.get("currencyTo");
        if (currencyFrom != null) {
            try {
                currencyService.checkCurrency(currencyFrom);
            } catch (CurrencyException e) {
                throw new BadParamsException("params currencyFrom not available -> " + currencyFrom,
                        BadParamsException.Reason.BAD_FORMAT);
            }
        }
        if (currencyTo != null) {
            try{
                currencyService.checkCurrency(currencyTo);
            } catch (CurrencyException e) {
                throw new BadParamsException("params currencyFrom not available -> " + currencyTo,
                        BadParamsException.Reason.BAD_FORMAT);
            }
        }
        String dataRange = allParams.get("dataRange");
        if (dataRange != null) {
            try {
                int range = Integer.parseInt(dataRange);
                if (range < 1) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                throw new BadParamsException("params dataRange (int > 0) not valid -> " + dataRange,
                        BadParamsException.Reason.BAD_FORMAT);
            }
        }
        String fromDate = allParams.get("fromDate");
        if ( fromDate != null) {
            try {
                LocalDate.parse(fromDate);
            } catch (Exception e) {
                throw new BadParamsException("params fromDate *(HHHH-mm-dd) not valid -> " + fromDate,
                        BadParamsException.Reason.BAD_FORMAT);
            }
        }
        String toDate = allParams.get("toDate");
        if ( toDate != null) {
            try {
                LocalDate.parse(toDate);
            } catch (Exception e) {
                throw new BadParamsException("params toDate *(HHHH-mm-dd) not valid -> " + toDate,
                        BadParamsException.Reason.BAD_FORMAT);
            }
        }
        return true;
    }

    public Set<String> getAvailableParams() {
        return Arrays.stream(Params.values())
                .map(Params::getName)
                .collect(Collectors.toSet());
    }

    public enum Params {
        CURRENCY_FROM("currencyFrom"),
        CURRENCY_TO("currencyTo"),
        FROM_DATE("fromDate"),
        TO_DATE("toDate"),
        DATA_RANGE("dataRange");

        private final String name;

        Params(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
