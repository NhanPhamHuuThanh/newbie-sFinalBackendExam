package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Plate;
import com.axonactive.backEndFinalExam.repository.PlateRepo;
import com.axonactive.backEndFinalExam.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlateServiceImpl implements PlateService {
    @Autowired
    private PlateRepo plateRepo;

    @Override
    public List<Plate> getAll() {
        return plateRepo.findAll();
    }

    @Override
    public Optional<Plate> findById(Integer id) {
        return plateRepo.findById(id);
    }

    @Override
    public Plate save(Plate plate) {
        return plateRepo.save(plate);
    }

    @Override
    public void deleteById(Integer id) {
        plateRepo.deleteById(id);
    }
}
