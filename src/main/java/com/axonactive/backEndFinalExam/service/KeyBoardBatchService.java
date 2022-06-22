package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyboardRequest;
import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KeyBoardBatchService {
    List<KeyboardBatch> getAll();

    Optional<KeyboardBatch> findById(Integer id);

    KeyboardBatch save(KeyboardBatch keyboardBatch);

    void deleteById(Integer id);

    KeyboardBatchDto checkStockAvailability(String name);

    KeyboardBatch saveWithExistData(KeyboardRequest keyboardRequest) throws ResourceNotFoundException;

    List<KeyboardBatch> listOfKeyboardBatchInAGivenDay(LocalDate date);

    List<KeyboardBatchDto> almostOutOfStockKeyBoardBatch() throws ResourceNotFoundException;

    List<KeyboardBatch> keyBoardBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException;
}
