package com.example.exchange.dao.criteria;

import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDAOCriteria {
    @PersistenceContext
    private EntityManager em;

    public List<String> findHistoryByRequestParam(Map<String, String> allParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<History> criteriaQuery = cb.createQuery(History.class);
        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<History, Currency> currencyJoin = historyRoot.join("fromCurrency", JoinType.INNER);
        criteriaQuery.where(cb.equal(currencyJoin.get("code"), "USD"));
        TypedQuery<History> query = em.createQuery(criteriaQuery);
        List<History> results = query.getResultList();
        System.out.println(results);
        return Collections.emptyList();
    }
}
