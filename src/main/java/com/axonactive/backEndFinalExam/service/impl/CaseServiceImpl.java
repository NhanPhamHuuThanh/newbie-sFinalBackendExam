package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Caze;
import com.axonactive.backEndFinalExam.repository.CaseRepo;
import com.axonactive.backEndFinalExam.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    private CaseRepo caseRepo;

    @Override
    public List<Caze> getAll() {
        return caseRepo.findAll();
    }

    @Override
    public Optional<Caze> findById(Integer id) {
        return caseRepo.findById(id);
    }

    @Override
    public Caze save(Caze caseInput) {
        return caseRepo.save(caseInput);
    }

    @Override
    public void deleteById(Integer id) {
        caseRepo.deleteById(id);
    }
}
