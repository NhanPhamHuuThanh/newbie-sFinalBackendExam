package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.repository.HousingRepo;
import com.axonactive.backEndFinalExam.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HousingServiceImpl implements HousingService {
    @Autowired
    private HousingRepo housingRepo;

    @Override
    public List<Housing> getAll() {
        return housingRepo.findAll();
    }

    @Override
    public Optional<Housing> findById(Integer id) {
        return housingRepo.findById(id);
    }

    @Override
    public Housing save(Housing housing) {
        return housingRepo.save(housing);
    }

    @Override
    public void deleteById(Integer id) {
    housingRepo.deleteById(id);
    }
}
