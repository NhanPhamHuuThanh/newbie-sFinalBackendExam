package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Stem;
import com.axonactive.backEndFinalExam.repository.StemRepo;
import com.axonactive.backEndFinalExam.service.StemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StemServiceImpl implements StemService {
    @Autowired
    private StemRepo stemRepo;

    @Override
    public List<Stem> getAll() {
        return stemRepo.findAll();
    }

    @Override
    public Optional<Stem> findById(Integer id) {
        return stemRepo.findById(id);
    }

    @Override
    public Stem save(Stem stem) {
        return stemRepo.save(stem);
    }

    @Override
    public void deleteById(Integer id) {
        stemRepo.deleteById(id);
    }
}
