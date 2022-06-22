package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.SwitchSpring;
import com.axonactive.backEndFinalExam.repository.SwitchSpringRepo;
import com.axonactive.backEndFinalExam.service.SwitchStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwitchStringServiceImpl implements SwitchStringService {
    @Autowired
    private SwitchSpringRepo switchStringRepo;

    @Override
    public List<SwitchSpring> getAll() {
        return switchStringRepo.findAll();
    }

    @Override
    public Optional<SwitchSpring> findById(Integer id) {
        return switchStringRepo.findById(id);
    }

    @Override
    public SwitchSpring save(SwitchSpring switchBatch) {
        return switchStringRepo.save(switchBatch);
    }

    @Override
    public void deleteById(Integer id) {
        switchStringRepo.deleteById(id);
    }
}
