package com.example.exchange.dto.request;

public class HistoryRequestDto {
    private String currencyFrom;
    private String currencyTo;
    private String dateFrom;
    private String dateTo;
    private int dataRange;

    public HistoryRequestDto(String currencyFrom, String currencyTo, String dateFrom, String dateTo, int dataRange) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dataRange = dataRange;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public int getDataRange() {
        return dataRange;
    }
}
