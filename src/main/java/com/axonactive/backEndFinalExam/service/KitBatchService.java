package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KitBatchRequest;
import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import com.axonactive.backEndFinalExam.service.dto.KitDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KitBatchService {
    List<KitBatch> findAll();

    Optional<KitBatch> findById(Integer id);

    KitBatch save(KitBatch kitBatch);

    void deleteById(Integer id);

    KitBatchDto checkStockAvailability(String name,String color);

    KitBatch createPriceUpdateRecord(KitBatchRequest kitBatchRequest) throws ResourceNotFoundException;

    List<KitBatch>  listOfAllKitBatchOnAGivenDay(LocalDate date);

    List<KitBatchDto> almostOutOfStockKitBatch() throws ResourceNotFoundException;

    List<KitBatch> kitBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException;

    KitDto findKitForCustomer(String name,String color);
}
