package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.repository.PriceUpdateRecordRepo;
import com.axonactive.backEndFinalExam.service.PriceUpdateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PriceUpdateRecordServiceImpl implements PriceUpdateRecordService {
    @Autowired
    PriceUpdateRecordRepo priceUpdateRecordRepo;

    @Override
    public List<PriceUpdateRecord> getAll() {
        return priceUpdateRecordRepo.findAll();
    }

    @Override
    public Optional<PriceUpdateRecord> findById(Integer id) {
        return priceUpdateRecordRepo.findById(id);
    }

    @Override
    public PriceUpdateRecord save(PriceUpdateRecord caseInput) {
        return priceUpdateRecordRepo.save(caseInput);
    }

    @Override
    public void deleteById(Integer id) {
    priceUpdateRecordRepo.deleteById(id);
    }

    @Override
    public List<PriceUpdateRecord> findAllTheUpdateHistoryOfKeyCapSet(String name, LocalDate date) {
        return priceUpdateRecordRepo.findAllTheUpdateHistoryOfKeyCapSet(name,date);
    }

    @Override
    public List<PriceUpdateRecord> findAllTheUpdateHistoryOfSwitchBatch(String name, LocalDate date) {
        return priceUpdateRecordRepo.findAllTheUpdateHistoryOfSwitchBatch(name,date);
    }

    @Override
    public List<PriceUpdateRecord> findAllTheUpdateHistoryOfKitBatch(String name, LocalDate date) {
        return priceUpdateRecordRepo.findAllTheUpdateHistoryOfKitBatch(name,date);
    }

    @Override
    public List<PriceUpdateRecord> findAllTheUpdateHistoryOfKeyboardBatch(String name, LocalDate date) {
        return priceUpdateRecordRepo.findAllTheUpdateHistoryOfKeyboardBatch(name,date);
    }
}
