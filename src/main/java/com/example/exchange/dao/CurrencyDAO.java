package com.example.exchange.dao;

import com.example.exchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDAO extends JpaRepository<Currency, Long> {
    Currency findByCode(String code);
}
