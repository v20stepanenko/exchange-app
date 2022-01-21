package com.example.exchange.controller;

import com.example.exchange.dto.response.AvailableCodeResponseDto;
import com.example.exchange.dto.response.CurrencyRateResponseDto;
import com.example.exchange.dto.response.HistoryResponseDto;
import com.example.exchange.service.CurrencyService;
import com.example.exchange.service.HistoryService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final HistoryService historyService;

    public CurrencyController(CurrencyService currencyService, HistoryService historyService) {
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    @GetMapping("/currency-codes")
    public AvailableCodeResponseDto currencyCodes() {
        AvailableCodeResponseDto availableCode = new AvailableCodeResponseDto();
        currencyService.getAll().forEach(currency -> availableCode.availableCode.add(currency.getCode()));
        return availableCode;
    }

    @GetMapping("/rates/{currencyFrom}/{currencyTo}")
    public CurrencyRateResponseDto currencyRate(@PathVariable String currencyFrom, @PathVariable String currencyTo) {
        float rate = currencyService.getRate(currencyFrom, currencyTo);
        return new CurrencyRateResponseDto(currencyFrom, currencyTo, rate);
    }

    @GetMapping("/history")
    public HistoryResponseDto history(@RequestParam Map<String,String> allParams){
        return new HistoryResponseDto(historyService.getByRequestParam(allParams));
    }
}
