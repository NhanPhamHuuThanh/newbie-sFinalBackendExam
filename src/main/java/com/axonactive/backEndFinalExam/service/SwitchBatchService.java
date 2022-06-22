package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.*;
import com.axonactive.backEndFinalExam.repository.SwitchBatchRepo;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import com.axonactive.backEndFinalExam.service.dto.SwitchDto;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SwitchBatchService {
    List<SwitchBatch> getAll();

    Optional<SwitchBatch> findById(Integer id);

    SwitchBatch save(SwitchBatch switchBatch);

    void deleteById(Integer id);

    SwitchBatchDto checkStockAvailability(String name);

    SwitchBatch saveWithExistData(SwitchBatchRequest switchBatchRequest) throws ResourceNotFoundException;

    List<SwitchBatch> listOfAllSwitchBatchOnAGivenDay(LocalDate date);

    List<SwitchBatch> switchBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException;

    List<SwitchBatchDto> almostOutOfStockSwitchBatch() throws ResourceNotFoundException;

    SwitchDto findSwitchForCustomer(String name);
}
