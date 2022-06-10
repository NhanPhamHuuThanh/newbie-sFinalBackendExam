package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.*;
import com.axonactive.backEndFinalExam.repository.SwitchStringRepo;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import com.axonactive.backEndFinalExam.service.SwitchStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwitchStringServiceImpl implements SwitchStringService {
    @Autowired
    private SwitchStringRepo switchStringRepo;

    @Override
    public List<SwitchString> getAll() {
        return switchStringRepo.findAll();
    }

    @Override
    public Optional<SwitchString> findById(Integer id) {
        return switchStringRepo.findById(id);
    }

    @Override
    public SwitchString save(SwitchString switchBatch) {
        return switchStringRepo.save(switchBatch);
    }

    @Override
    public void deleteById(Integer id) {
        switchStringRepo.deleteById(id);
    }
}
