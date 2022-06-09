package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;

import java.util.List;
import java.util.Optional;

public interface StabAndKitBatchService {
    List<StabAndKitBatch> getAll();

    Optional<StabAndKitBatch> findById(Integer id);

    StabAndKitBatch save(StabAndKitBatch stabAndKitBatch);

    void deleteById(Integer id);
}
