package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyCapSetRequest;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KeycapSetService {
    List<KeyCapSet> getAll();

    Optional<KeyCapSet> findById(Integer id);

    KeyCapSet save(KeyCapSet keyCapSet);

    void deleteById(Integer id);

    KeyCapSetDto getAllInStock(String name);

    List<KeyCapSet> getAllKeyCapSetWithNameAndImportedDate(String name, LocalDate date);

    List<KeyCapSet>getAllKeyCapSetWithImportedDate(LocalDate date);

    KeyCapSet saveARequest(KeyCapSetRequest keyCapSetRequest) throws ResourceNotFoundException;

    List<KeyCapSet> getAllKeyCapSetFromAManufacturer(String name);

    List<KeyCapSetDto> almostOutOfStockKeyCapSet() throws ResourceNotFoundException;

    List<KeyCapSet> keyCapSetBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException;
}
