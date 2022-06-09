package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.Plate;

import java.util.List;
import java.util.Optional;

public interface PlateService {
    List<Plate> getAll();

    Optional<Plate> findById(Integer id);

    Plate save(Plate plate);

    void deleteById(Integer id);
}
