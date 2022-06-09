package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KitBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitPatchRepo  extends JpaRepository<KitBatch,Integer> {
}
