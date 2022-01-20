package com.example.exchange.controller;

import com.example.exchange.dto.mapper.CurrencyMapper;
import com.example.exchange.dto.response.AvailableCode;
import com.example.exchange.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrencyController {

    CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency-codes")
    public AvailableCode currencyCodes(){
        AvailableCode availableCode = new AvailableCode();
        currencyService.getAll().forEach(currency -> availableCode.availableCode.add(currency.getCode()));
        return availableCode;
    }
}
