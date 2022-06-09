package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.repository.KeyboardBatchRepo;
import com.axonactive.backEndFinalExam.service.KeyBoardBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class KeyBoardBatch implements KeyBoardBatchService {
    @Autowired
    private KeyboardBatchRepo keyboardBatchRepo;


    @Override
    public List<KeyboardBatch> getAll() {
        return keyboardBatchRepo.findAll();
    }

    @Override
    public Optional<KeyboardBatch> findById(Integer id) {
        return keyboardBatchRepo.findById(id);
    }

    @Override
    public KeyboardBatch save(KeyboardBatch keyboardBatch) {
        return keyboardBatchRepo.save(keyboardBatch);
    }

    @Override
    public void deleteById(Integer id) {
    keyboardBatchRepo.deleteById(id);
    }
}
