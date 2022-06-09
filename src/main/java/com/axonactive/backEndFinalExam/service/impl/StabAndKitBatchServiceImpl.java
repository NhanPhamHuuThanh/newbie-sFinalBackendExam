package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;
import com.axonactive.backEndFinalExam.repository.StabAndKitPatchRepo;
import com.axonactive.backEndFinalExam.service.StabAndKitBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StabAndKitBatchServiceImpl implements StabAndKitBatchService {
    @Autowired
    private StabAndKitPatchRepo stabAndKitPatchRepo;

    @Override
    public List<StabAndKitBatch> getAll() {
        return stabAndKitPatchRepo.findAll();
    }

    @Override
    public Optional<StabAndKitBatch> findById(Integer id) {
        return stabAndKitPatchRepo.findById(id);
    }

    @Override
    public StabAndKitBatch save(StabAndKitBatch stabAndKitBatch) {
        return stabAndKitPatchRepo.save(stabAndKitBatch);
    }

    @Override
    public void deleteById(Integer id) {
    stabAndKitPatchRepo.deleteById(id);
    }
}
