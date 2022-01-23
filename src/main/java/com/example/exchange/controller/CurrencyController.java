package com.example.exchange.controller;

import com.example.exchange.configure.DataInjector;
import com.example.exchange.dto.response.AvailableCodeResponseDto;
import com.example.exchange.dto.response.CurrencyRateResponseDto;
import com.example.exchange.dto.response.HistoryResponseDto;
import com.example.exchange.exceptions.BadParamsException;
import com.example.exchange.service.CurrencyService;
import com.example.exchange.service.HistoryService;
import com.example.exchange.validation.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CurrencyController {

    private final CurrencyService currencyService;
    private final HistoryService historyService;
    private final ApplicationContext applicationContext;

    public CurrencyController(CurrencyService currencyService, HistoryService historyService, ApplicationContext applicationContext) {
        this.currencyService = currencyService;
        this.historyService = historyService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/currency-codes")
    public AvailableCodeResponseDto currencyCodes() {
        AvailableCodeResponseDto availableCode = new AvailableCodeResponseDto();
        availableCode.addCodes(currencyService.getAll());
        return availableCode;
    }

    @GetMapping("/rates/{currencyFrom}/{currencyTo}")
    @ResponseStatus(HttpStatus.CREATED)
    public CurrencyRateResponseDto currencyRate(@PathVariable @Currency String currencyFrom, @PathVariable @Currency String currencyTo) {
        BigDecimal rate = currencyService.getRate(currencyFrom, currencyTo);
        return new CurrencyRateResponseDto(currencyFrom, currencyTo, rate);
    }

    @GetMapping("/history")
    public HistoryResponseDto history(@RequestParam Map<String,String> allParams){
        historyService.checkParams(allParams);
        return new HistoryResponseDto(historyService.getByRequestParam(allParams));
    }

    @GetMapping("/inject")
    public String inject() {
        DataInjector dataInjector = applicationContext.getBean(DataInjector.class);
        dataInjector.inject();
        return "Inject data, look at console";
    }

    @ExceptionHandler(value = BadParamsException.class)
    public ResponseEntity<Map<String, Object>> exceptionHistoryHandler(BadParamsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", 400);
        body.put("exception", ex.getMessage());
        if (ex.getReason() == BadParamsException.Reason.BAD_PARAMS) {
            body.put("availableParams", historyService.getAvailableParams());
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
