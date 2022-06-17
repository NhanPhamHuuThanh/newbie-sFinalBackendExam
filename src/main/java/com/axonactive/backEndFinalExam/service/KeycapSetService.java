package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.api.request.KeyCapSetRequest;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KeycapSetService {
    List<KeyCapSet> getAll();

    Optional<KeyCapSet> findById(Integer id);

    KeyCapSet save(KeyCapSet keyCapSet);

    void deleteById(Integer id);

    KeyCapSetDto getAllInStock(String name);

    List<KeyCapSet> getAllKeyCapSetWithNameAndImportedDate(String name, LocalDate date);

    List<KeyCapSet>getAllKeyCapSetWithImportedDate(LocalDate date);

    KeyCapSetDto saveARequest(KeyCapSetRequest keyCapSetRequest);
}
