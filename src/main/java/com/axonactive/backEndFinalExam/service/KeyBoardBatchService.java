package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;

import java.util.List;
import java.util.Optional;

public interface KeyBoardBatchService {
    List<KeyboardBatch> getAll();

    Optional<KeyboardBatch> findById(Integer id);

    KeyboardBatch save(KeyboardBatch keyboardBatch);

    void deleteById(Integer id);
}
