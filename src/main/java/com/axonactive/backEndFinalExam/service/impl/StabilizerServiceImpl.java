package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Stabilizer;
import com.axonactive.backEndFinalExam.repository.StabilizerRepo;
import com.axonactive.backEndFinalExam.service.StabilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StabilizerServiceImpl implements StabilizerService {
    @Autowired
    private StabilizerRepo stabilizerRepo;

    @Override
    public List<Stabilizer> getAll() {
        return stabilizerRepo.findAll();
    }

    @Override
    public Optional<Stabilizer> findById(Integer id) {
        return stabilizerRepo.findById(id);
    }

    @Override
    public Stabilizer save(Stabilizer stabilizer) {
        return stabilizerRepo.save(stabilizer);
    }

    @Override
    public void deleteById(Integer id) {
        stabilizerRepo.deleteById(id);
    }
}
