package com.example.exchange.validation;

import com.example.exchange.dao.CurrencyDAO;
import com.example.exchange.exceptions.CurrencyException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CurrencyValidation implements ConstraintValidator<Currency, String> {
    CurrencyDAO currencyDAO;

    public CurrencyValidation(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public boolean isValid(String codeCurrency, ConstraintValidatorContext constraintValidatorContext) {
        if(codeCurrency == null) {
            return false;
        }
        com.example.exchange.model.Currency byCode = currencyDAO.findByCode(codeCurrency);
        if (byCode == null) {
            throw new CurrencyException("Not available currency: " + codeCurrency);
        }
        return byCode != null;
    }
}
