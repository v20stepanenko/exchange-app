package com.example.exchange.configure;

import com.example.exchange.model.Currency;
import com.example.exchange.service.CurrencyService;
import com.example.exchange.service.ExchangerHttpClient;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppInit {
    private final ExchangerHttpClient exchangerHttpClient;
    private final CurrencyService currencyService;
    private final DataInjector dataInjector;

    public AppInit(ExchangerHttpClient exchangerHttpClient, CurrencyService currencyService, DataInjector dataInjector) {
        this.exchangerHttpClient = exchangerHttpClient;
        this.currencyService = currencyService;
        this.dataInjector = dataInjector;
    }

    @PostConstruct
    private void getCurrencies() {
        List<String> rawEnableCurrency = exchangerHttpClient.getEnableCurrency();
        List<Currency> enableCurrency = rawEnableCurrency.stream()
                .map(Currency::new)
                .collect(Collectors.toList());
        currencyService.saveAll(enableCurrency);
        dataInjector.inject();
    }
}
