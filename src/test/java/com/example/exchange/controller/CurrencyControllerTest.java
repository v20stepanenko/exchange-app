package com.example.exchange.controller;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.dao.HistoryDAO;
import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Transactional
class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CurrencyDAO currencyDAO;
    @Autowired
    private HistoryDAO historyDAO;
    private static int countHistories;

    @BeforeEach
    public void setUp() {
        Currency uah = currencyDAO.findByCode("UAH");
        Currency usd = currencyDAO.findByCode("USD");
        Currency eur = currencyDAO.findByCode("EUR");
        LocalDateTime time = LocalDateTime.parse("2022-01-20T19:34:50.63");
        List<History> historyList = new LinkedList<>() {{
            add(new History(usd, uah, BigDecimal.valueOf(26), time));
            add(new History(usd, uah, BigDecimal.valueOf(26), time.plusDays(1)));
            add(new History(usd, uah, BigDecimal.valueOf(26), time.plusWeeks(1)));
            add(new History(usd, uah, BigDecimal.valueOf(26), time.plusMonths(1)));
            add(new History(eur, uah, BigDecimal.valueOf(38), time));
            add(new History(eur, uah, BigDecimal.valueOf(38), time.plusDays(1)));
            add(new History(eur, uah, BigDecimal.valueOf(38), time.plusWeeks(1)));
            add(new History(eur, uah, BigDecimal.valueOf(38), time.plusMonths(1)));
            add(new History(eur, usd, BigDecimal.valueOf(1.1), time));
            add(new History(eur, usd, BigDecimal.valueOf(1.1), time.plusDays(1)));
            add(new History(eur, usd, BigDecimal.valueOf(1.1), time.plusWeeks(1)));
            add(new History(eur, usd, BigDecimal.valueOf(1.1), time.plusMonths(1)));
        }};
        historyDAO.saveAll(historyList);
        countHistories = (int) historyDAO.count();
        if (countHistories == 0) {
            throw new RuntimeException("You don't have histories in DB");
        }
    }

    @AfterEach
    public void cleanUp() {
        historyDAO.deleteAll();
        currencyDAO.deleteAll();
    }


    @Test
    public void availableCodeLoads() throws Exception {

        this.mockMvc.perform(get("/currency-codes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.availableCode").isArray())
                .andExpect(jsonPath("$.availableCode", hasItem("USD")))
                .andExpect(jsonPath("$.availableCode", hasItem("EUR")))
                .andExpect(jsonPath("$.availableCode", hasItem("UAH")));
    }

    @Test
    public void ratesCurrencyUSD_UAH() throws Exception {

        this.mockMvc.perform(get("/rates/USD/UAH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.currencyFrom").value("USD"))
                .andExpect(jsonPath("$.currencyTo").value("UAH"))
                .andExpect(jsonPath("$.rate").isNumber());
    }

    @Test
    public void ratesCurrencyUSD_USD() throws Exception {

        this.mockMvc.perform(get("/rates/USD/USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.currencyFrom").value("USD"))
                .andExpect(jsonPath("$.currencyTo").value("USD"))
                .andExpect(jsonPath("$.rate").value("1.0"));
    }

    @Test
    public void historyAll_Ok() throws Exception {
        this.mockMvc.perform(get("/history"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(countHistories)))
                .andExpect(content().string(containsString(
                        "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\",\"rate\":26,\"time\":\"2022-01-20T19:34:50.63\"}"
                )));
    }

    @Test
    public void historyByFromCurrencyUAH_empty_Ok() throws Exception {

        Thread.sleep(400);
        this.mockMvc.perform(get("/history?fromCurrency=UAH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(0)));
    }

    @Test
    public void historyByFromCurrencyUSD_Ok() throws Exception {

        this.mockMvc.perform(get("/history?fromCurrency=USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(4)))
                .andExpect(content().json("{\"histories\":"
                        + "[{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-02-20T19:34:50.63\"}]}"));
    }

    @Test
    public void historyByFromCurrencyUsdToEurEmpty_Ok() throws Exception {
        this.mockMvc.perform(get("/history?fromCurrency=USD&toCurrency=EUR"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(0)));
    }

    @Test
    public void historyByFromCurrencyUsdToUah_Ok() throws Exception {
        this.mockMvc.perform(get("/history?fromCurrency=USD&toCurrency=UAH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(4)))
                .andExpect(content().json("{\"histories\":"
                        + "[{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-02-20T19:34:50.63\"}]}"));
    }

    @Test
    public void historyByToCurrencyUAH_Ok() throws Exception {
        this.mockMvc.perform(get("/history?toCurrency=UAH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(8)))
                .andExpect(content().json("{\"histories\":"
                        + "[{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-02-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-21T19:34:50.63\"},{"
                        + "\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-02-20T19:34:50.63\"}]}"));
    }


    @Test
    public void historyByDate_Ok() throws Exception {
        this.mockMvc.perform(get("/history?fromDate=2022-02-02"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(3)))
                .andExpect(content().json("{\"histories\":"
                        + "[{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26,\"time\":\"2022-02-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38,\"time\":\"2022-02-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"USD\","
                        + "\"rate\":1.1,\"time\":\"2022-02-20T19:34:50.63\"}]}"));

        // I'm tired of writing such tests

        this.mockMvc.perform(get("/history?toDate=2022-02-02"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(9)))
                .andExpect(content().json("{\"histories\":"
                        + "[{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"USD\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":26.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"UAH\","
                        + "\"rate\":38.00,\"time\":\"2022-01-27T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"USD\","
                        + "\"rate\":1.10,\"time\":\"2022-01-20T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"USD\","
                        + "\"rate\":1.10,\"time\":\"2022-01-21T19:34:50.63\"},"
                        + "{\"currencyFromCode\":\"EUR\",\"currencyToCode\":\"USD\","
                        + "\"rate\":1.10,\"time\":\"2022-01-27T19:34:50.63\"}]}"));
    }

    // I'm exhausted of writing such tests, enough

    @Test
    public void historyByRangeData_Ok() throws Exception {

        this.mockMvc.perform(get("/history?fromCurrency=USD&dataRange=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.histories", hasSize(2)));
    }

    //----------------------------------------

    // ... let's imagine is the finish line

}