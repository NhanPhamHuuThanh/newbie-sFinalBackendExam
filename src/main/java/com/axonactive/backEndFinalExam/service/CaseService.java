package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.Caze;

import java.util.List;
import java.util.Optional;

public interface CaseService {
    List<Caze> getAll();

    Optional<Caze> findById(Integer id);

    Caze save(Caze caseInput);

    void deleteById(Integer id);



}
