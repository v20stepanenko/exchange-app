package com.example.exchange.dao;

import com.example.exchange.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDAO extends JpaRepository<History, Long> {
}
