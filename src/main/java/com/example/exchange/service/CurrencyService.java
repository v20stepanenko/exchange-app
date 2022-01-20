package com.example.exchange.service;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyDAO currencyDAO;
    ExchangerHttpClient exchangerHttpClient;
    HistoryDAO historyDAO;

    public CurrencyService(CurrencyDAO currencyDAO, ExchangerHttpClient exchangerHttpClient, HistoryDAO historyDAO) {
        this.currencyDAO = currencyDAO;
        this.exchangerHttpClient = exchangerHttpClient;
        this.historyDAO = historyDAO;
    }

    public void saveAll(List<Currency> currencies){
        currencyDAO.saveAll(currencies);
    }

    public List<Currency> getAll(){
        return currencyDAO.findAll();
    }

    public float getRate(String currencyFrom, String currencyTo) {
        Currency currencyEntityFrom = currencyDAO.findByCode(currencyFrom);
        Currency currencyEntityTo = currencyDAO.findByCode(currencyTo);
        float rate = exchangerHttpClient.getRate(currencyFrom, currencyTo);
        History history = new History(currencyEntityFrom, currencyEntityTo, rate, LocalDateTime.now());
        historyDAO.save(history);
        return exchangerHttpClient.getRate(currencyFrom, currencyTo);
    }
}
