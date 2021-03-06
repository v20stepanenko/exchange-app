package com.example.exchange.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class ExchangerHttpClient {
    private static final String API_KEY = "2da8a6179931269acda3532c";
    private static final String URL = "https://v6.exchangerate-api.com/v6/";
    private final ObjectMapper objectMapper;
    public ExchangerHttpClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<String> getEnableCurrency() {
        final String URI = URL + API_KEY + "latest/USD";
        try {
            JsonNode node = objectMapper.readTree(getData(URI));
            JsonNode leaf = node.get("conversion_rates");
            if (leaf != null) {
                List<String> keys = new ArrayList<>();
                Iterator<String> iterator = leaf.fieldNames();
                iterator.forEachRemaining(keys::add);
                return keys;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public BigDecimal getRate(String currencyFrom, String currencyTo) {
        final String URI = URL + API_KEY + "/pair/" + currencyFrom + "/" + currencyTo;
        try {
            JsonNode node = objectMapper.readTree(getData(URI));
            JsonNode leaf = node.get("conversion_rate");
            return BigDecimal.valueOf(Float.parseFloat(leaf.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getData(String uri) {
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            new RuntimeException("Cant get data from " + uri, e);
        }
        return result;
    }
}
