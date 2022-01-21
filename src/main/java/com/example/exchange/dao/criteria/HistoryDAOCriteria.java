package com.example.exchange.dao.criteria;

import com.example.exchange.model.Currency;
import com.example.exchange.model.History;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDAOCriteria {
    @PersistenceContext
    private EntityManager em;

    public List<History> findHistoryByRequestParam(Map<String, String> allParams) {
        int rangeData = Integer.MAX_VALUE;
        String rangeParam = allParams.get("dataRange");
        if (!Objects.isNull(rangeParam)) {
            rangeData = Integer.parseInt(rangeParam);
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<History> criteriaQuery = cb.createQuery(History.class);
        Root<History> historyRoot = criteriaQuery.from(History.class);
        List<Predicate> predicates = new LinkedList<>();

        String currencyFrom = allParams.get("fromCurrency");
        if (!Objects.isNull(currencyFrom)) {
            Join<History, Currency> currencyFromJoin = historyRoot.join("fromCurrency", JoinType.INNER);
            predicates.add(cb.equal(currencyFromJoin.get("code"), currencyFrom));
        }
        String toCurrency = allParams.get("toCurrency");
        if (!Objects.isNull(toCurrency)) {
            Join<History, Currency> currencyToJoin = historyRoot.join("toCurrency", JoinType.INNER);
            predicates.add(cb.equal(currencyToJoin.get("code"), toCurrency));
        }

        String fromDate = allParams.get("fromDate");
        if (!Objects.isNull(fromDate)) {
            LocalDate dateTime = LocalDate.parse(fromDate);
            predicates.add(cb.greaterThan(historyRoot.get("timeStamp").as(LocalDate.class), dateTime));
        }

        String toDate = allParams.get("toDate");
        if (!Objects.isNull(toDate)) {
            LocalDate dateTime = LocalDate.parse(toDate);
            predicates.add(cb.lessThan(historyRoot.get("timeStamp").as(LocalDate.class), dateTime));
        }


        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<History> query = em.createQuery(criteriaQuery);
        return query.setFirstResult(0).setMaxResults(rangeData).getResultList();
    }
}
