package com.modernization.application.numerology.service;

import com.modernization.application.numerology.repository.FormulaRepository;
import com.modernization.application.numerology.service.core.NumerologyValue;
import com.modernization.application.numerology.service.exception.NumerologyCustomException;
import com.modernization.application.numerology.service.model.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormulaService {

    private final FormulaRepository repository;

    @Autowired
    public FormulaService(FormulaRepository repository) {
        this.repository = repository;
    }

    public Formula findFormulaByCategory(NumerologyValue.Category category) {
        if(category.getName().isEmpty()) {
            throw new NumerologyCustomException("The category can't be empty");
        }
        return repository.findByCategory(category.getName());
    }
}
