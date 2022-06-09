package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchBatchRepo extends JpaRepository<SwitchBatch,Integer> {
}
