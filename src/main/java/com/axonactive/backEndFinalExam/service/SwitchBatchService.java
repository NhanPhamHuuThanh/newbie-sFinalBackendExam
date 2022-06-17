package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.*;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SwitchBatchService {
    List<SwitchBatch> getAll();

    Optional<SwitchBatch> findById(Integer id);

    SwitchBatch save(SwitchBatch switchBatch);

    void deleteById(Integer id);

    SwitchBatchDto checkStockAvailability(String name);

}
