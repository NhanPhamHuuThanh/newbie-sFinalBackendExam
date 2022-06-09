package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.repository.KeycapSetRepo;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class KeycabSetServiceImpl implements KeycapSetService {
    @Autowired
    private KeycapSetRepo keycapSetRepo;

    @Override
    public List<KeyCapSet> getAll() {
        return keycapSetRepo.findAll();
    }

    @Override
    public Optional<KeyCapSet> findById(Integer id) {
        return keycapSetRepo.findById(id);
    }

    @Override
    public KeyCapSet save(KeyCapSet keyCapSet) {
        return keycapSetRepo.save(keyCapSet);
    }

    @Override
    public void deleteById(Integer id) {
    keycapSetRepo.deleteById(id);
    }
}
