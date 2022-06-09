package com.axonactive.backEndFinalExam.service;


import com.axonactive.backEndFinalExam.entity.Stabilizer;

import java.util.List;
import java.util.Optional;

public interface StabilizerService {
    List<Stabilizer> getAll();

    Optional<Stabilizer> findById(Integer id);

    Stabilizer save(Stabilizer stabilizer);

    void deleteById(Integer id);
}
