package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KeyboardBatchRepo extends JpaRepository<KeyboardBatch, Integer> {
    @Query(" from KeyboardBatch where name = :name and status = 'INSTOCK' ")
    List<KeyboardBatch> getAllInStock(@Param("name") String name);

    @Query(" from KeyboardBatch where name = :name")
    List<KeyboardBatch> getSoldUnits(@Param("name") String name);

    @Query("select k from KeyboardBatch k, Manufacturer m where m.id = k.manufacturer.id and m.name = :name")
    List<KeyboardBatch> getAllKeyboardBatchFromAManufacturer(@Param("name") String name);

    @Query("from  KeyboardBatch k where " +
            "k.importedDate = :date ")
    List<KeyboardBatch> getAllKeyboardBatchFromADay(@Param("date") LocalDate date);



}
