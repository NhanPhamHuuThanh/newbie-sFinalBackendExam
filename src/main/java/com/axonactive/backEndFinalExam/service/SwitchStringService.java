package com.axonactive.backEndFinalExam.service;


import com.axonactive.backEndFinalExam.entity.SwitchString;

import java.util.List;
import java.util.Optional;

public interface SwitchStringService {
    List<SwitchString> getAll();

    Optional<SwitchString> findById(Integer id);

    SwitchString save(SwitchString switchString);

    void deleteById(Integer id);
}
