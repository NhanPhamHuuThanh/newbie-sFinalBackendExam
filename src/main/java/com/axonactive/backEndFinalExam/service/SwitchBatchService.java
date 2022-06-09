package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.SwitchBatch;

import java.util.List;
import java.util.Optional;

public interface SwitchBatchService {
    List<SwitchBatch> getAll();

    Optional<SwitchBatch> findById(Integer id);

    SwitchBatch save(SwitchBatch switchBatch);

    void deleteById(Integer id);
}
