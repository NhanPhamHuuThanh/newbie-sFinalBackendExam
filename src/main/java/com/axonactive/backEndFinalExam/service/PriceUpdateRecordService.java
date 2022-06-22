package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PriceUpdateRecordService {

    List<PriceUpdateRecord> getAll();

    Optional<PriceUpdateRecord> findById(Integer id);

    PriceUpdateRecord save(PriceUpdateRecord caseInput);

    void deleteById(Integer id);

    List<PriceUpdateRecord> findAllTheUpdateHistoryOfKeyCapSet(String name, LocalDate date);

    List<PriceUpdateRecord> findAllTheUpdateHistoryOfSwitchBatch(String name, LocalDate date);


    List<PriceUpdateRecord> findAllTheUpdateHistoryOfKitBatch(String name, LocalDate date);

    List<PriceUpdateRecord> findAllTheUpdateHistoryOfKeyboardBatch(String name, LocalDate date);
}
