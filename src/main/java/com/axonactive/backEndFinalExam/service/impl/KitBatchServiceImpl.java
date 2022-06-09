package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.repository.KitPatchRepo;
import com.axonactive.backEndFinalExam.service.KitPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitBatchServiceImpl implements KitPatchService {
    @Autowired
    private KitPatchRepo kitPatchRepo;

    @Override
    public List<KitBatch> findAll() {
        return kitPatchRepo.findAll();
    }

    @Override
    public Optional<KitBatch> findById(Integer id) {
        return kitPatchRepo.findById(id);
    }

    @Override
    public KitBatch save(KitBatch kitBatch) {
        return kitPatchRepo.save(kitBatch);
    }

    @Override
    public void deleteById(Integer id) {
        kitPatchRepo.deleteById(id);
    }
}
