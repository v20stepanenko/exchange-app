package com.example.exchange.controller;

import com.example.exchange.exceptions.CurrencyException;
import com.example.exchange.service.HistoryService;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CurrencyGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    HistoryService historyService;

    public CurrencyGlobalExceptionHandler(HistoryService historyService) {
        this.historyService = historyService;
    }

    @ExceptionHandler(value = CurrencyException.class)
    public ResponseEntity<Map<String, Object>> handleMethodCurrencyNotValid(CurrencyException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", 400);
        body.put("exception", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
