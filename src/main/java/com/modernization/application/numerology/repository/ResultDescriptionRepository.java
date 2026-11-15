package com.modernization.application.numerology.repository;

import com.modernization.application.numerology.service.model.ResultDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDescriptionRepository extends JpaRepository<ResultDescription, Long> {

    List<ResultDescription> findByLanguage(String language);
    ResultDescription findByCategoryAndLanguage(String category, String language);
}
