package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.KitBatch;

import java.util.List;
import java.util.Optional;

public interface KitPatchService {
    List<KitBatch> findAll();

    Optional<KitBatch> findById(Integer id);

    KitBatch save(KitBatch kitBatch);

    void deleteById(Integer id);
}
