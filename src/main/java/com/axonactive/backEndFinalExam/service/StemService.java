package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.Stem;

import java.util.List;
import java.util.Optional;

public interface StemService {
    List<Stem> getAll();

    Optional<Stem> findById(Integer id);

    Stem save(Stem stem);

    void deleteById(Integer id);
}
