package com.example.exchange.service;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.exceptions.CurrencyException;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    public Currency getByCode(String code){
        return currencyDAO.findByCode(code);
    }

    public BigDecimal getRate(String currencyFrom, String currencyTo) {
        Currency currencyEntityFrom = checkCurrency(currencyFrom);
        Currency currencyEntityTo = checkCurrency(currencyTo);
        BigDecimal rate = exchangerHttpClient.getRate(currencyFrom, currencyTo);
        History history = new History(currencyEntityFrom, currencyEntityTo, rate, LocalDateTime.now());
        historyDAO.save(history);
        return exchangerHttpClient.getRate(currencyFrom, currencyTo);
    }

    public Currency checkCurrency(String currencyCode){
        Currency currency = getByCode(currencyCode);
        if (Objects.isNull(currency)) {
            throw new CurrencyException("Not available currency, check your code: " + currencyCode);
        }
        return currency;
    }
}
