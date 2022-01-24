package com.example.exchange.configure;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final CurrencyDAO currencyDAO;
    private final HistoryDAO historyDAO;

    public DataInjector(CurrencyDAO currencyDAO, HistoryDAO historyDAO) {
        this.currencyDAO = currencyDAO;
        this.historyDAO = historyDAO;
    }

    public void inject() {
        LocalDateTime time = LocalDateTime.parse("2022-01-20T19:34:50.63");
        injectHistory("UAH", "USD", BigDecimal.valueOf(28.5), time);
        injectHistory("UAH", "USD", BigDecimal.valueOf(28.6), time.plusDays(1));
        injectHistory("UAH", "EUR", BigDecimal.valueOf(30.6), time);
        injectHistory("UAH", "EUR", BigDecimal.valueOf(31.6), time.plusMonths(1));
        injectHistory("UAH", "RUB", BigDecimal.valueOf(0.6), time.plusDays(10));
    }

    private void injectHistory(String cFrom, String cTo, BigDecimal rate, LocalDateTime time) {
        System.out.println("Inject HISTORY DATA to DB !!!! ");
        Currency currencyFrom = currencyDAO.findByCode(cFrom);
        Currency currencyTo = currencyDAO.findByCode(cTo);
        History history = new History(currencyFrom, currencyTo, rate, time);
        historyDAO.save(history);
        System.out.println(history);
    }
}
