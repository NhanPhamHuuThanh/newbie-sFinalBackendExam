package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StabAndKitBatchRepo extends JpaRepository<StabAndKitBatch, Integer> {
}
