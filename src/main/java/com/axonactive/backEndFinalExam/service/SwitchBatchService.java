package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.*;

import java.util.List;
import java.util.Optional;

public interface SwitchBatchService {
    List<SwitchBatch> getAll();

    Optional<SwitchBatch> findById(Integer id);

    SwitchBatch save(SwitchBatch switchBatch);

    SwitchBatch save(SwitchBatchRequest switchBatchRequest);

    void deleteById(Integer id);
}
