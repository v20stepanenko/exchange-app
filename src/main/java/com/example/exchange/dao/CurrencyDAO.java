package com.example.exchange.dao;

import com.example.exchange.model.Currency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDAO extends JpaRepository<Currency, Long> {
    @Override
    <S extends Currency> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Currency> findAll();
}
