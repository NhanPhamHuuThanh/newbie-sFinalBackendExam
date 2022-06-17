package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyboardBatchRepo extends JpaRepository<KeyboardBatch, Integer> {
    @Query(" from KeyboardBatch where name = :name and status = 'INSTOCK' ")
    List<KeyboardBatch> getAllInStock(@Param("name") String name);

    @Query(" from KeyboardBatch where name = :name")
    List<KeyboardBatch> getSoldUnits(@Param("name") String name);
}
