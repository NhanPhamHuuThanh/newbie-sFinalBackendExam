package com.axonactive.backEndFinalExam.service;


import com.axonactive.backEndFinalExam.entity.SwitchSpring;

import java.util.List;
import java.util.Optional;

public interface SwitchStringService {
    List<SwitchSpring> getAll();

    Optional<SwitchSpring> findById(Integer id);

    SwitchSpring save(SwitchSpring switchString);

    void deleteById(Integer id);
}
