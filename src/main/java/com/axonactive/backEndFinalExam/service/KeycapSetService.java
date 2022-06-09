package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;

import java.util.List;
import java.util.Optional;

public interface KeycapSetService {
    List<KeyCapSet> getAll();

    Optional<KeyCapSet> findById(Integer id);

    KeyCapSet save(KeyCapSet keyCapSet);

    void deleteById(Integer id);
}
