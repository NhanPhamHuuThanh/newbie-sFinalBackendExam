package com.axonactive.backEndFinalExam.service;


import com.axonactive.backEndFinalExam.entity.Housing;

import java.util.List;
import java.util.Optional;

public interface HousingService {
    List<Housing> getAll();

    Optional<Housing> findById(Integer id);

    Housing save(Housing housing);

    void deleteById(Integer id);
}
