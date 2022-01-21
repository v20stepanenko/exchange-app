package com.example.exchange.configure;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final CurrencyDAO currencyDAO;
    private final HistoryDAO historyDAO;

    public DataInjector(CurrencyDAO currencyDAO, HistoryDAO historyDAO) {
        this.currencyDAO = currencyDAO;
        this.historyDAO = historyDAO;
    }

    @PostConstruct
    private void init() {
        LocalDateTime time = LocalDateTime.parse("2022-01-20T19:34:50.63");
        injectHistory("UAH", "USD", 28.5f, time);
        injectHistory("UAH", "USD", 28.6f, time.plusDays(1));
        injectHistory("UAH", "EUR", 30.6f, time);
        injectHistory("UAH", "EUR", 31.6f, time.plusMonths(1));
        injectHistory("UAH", "RUB", 0.6f, time.plusDays(10));
    }

    private void injectHistory(String cFrom, String cTo, float rate, LocalDateTime time) {
        System.out.println("Inject DATA !!!! ");
        Currency currencyFrom = currencyDAO.findByCode(cFrom);
        Currency currencyTo = currencyDAO.findByCode(cTo);
        History history = new History(currencyFrom, currencyTo, rate, time);
        historyDAO.save(history);
        System.out.println(history);
    }
}
