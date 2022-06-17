package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.api.request.CustomKeyboardRequest;
import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.service.dto.CustomKeyboardDto;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;

import java.util.List;
import java.util.Optional;

public interface KeyBoardBatchService {
    List<KeyboardBatch> getAll();

    Optional<KeyboardBatch> findById(Integer id);

    KeyboardBatch save(KeyboardBatch keyboardBatch);

    void deleteById(Integer id);

    KeyboardBatchDto checkStockAvailability(String name);



}
