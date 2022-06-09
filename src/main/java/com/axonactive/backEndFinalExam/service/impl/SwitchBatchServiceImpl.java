package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import com.axonactive.backEndFinalExam.repository.SwitchBatchRepo;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwitchBatchServiceImpl implements SwitchBatchService {
    @Autowired
    private SwitchBatchRepo switchBatchRepo;

    @Override
    public List<SwitchBatch> getAll() {
        return switchBatchRepo.findAll();
    }

    @Override
    public Optional<SwitchBatch> findById(Integer id) {
        return switchBatchRepo.findById(id);
    }

    @Override
    public SwitchBatch save(SwitchBatch switchBatch) {
        return switchBatchRepo.save(switchBatch);
    }

    @Override
    public void deleteById(Integer id) {
        switchBatchRepo.deleteById(id);
    }
}
