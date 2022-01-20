package com.example.exchange.service;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.model.Currency;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyDAO currencyDAO;

    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public void saveAll(List<Currency> currencies){
        currencyDAO.saveAll(currencies);
    }

    public List<Currency> getAll(){
        return currencyDAO.findAll();
    }
}
