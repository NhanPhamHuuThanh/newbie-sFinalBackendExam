package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyboardBatchRepo extends JpaRepository<KeyboardBatch,Integer> {
}
