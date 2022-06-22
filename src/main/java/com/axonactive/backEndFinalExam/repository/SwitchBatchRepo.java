package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SwitchBatchRepo extends JpaRepository<SwitchBatch, Integer> {
    @Query(" from SwitchBatch where switchName Like :name and status = 'INSTOCK'")
    List<SwitchBatch> getAllInstock(@Param("name") String name);

    @Query(" from SwitchBatch where switchName = :name")
    List<SwitchBatch> getSoldUnits(@Param("name") String name);

    @Query("select k from SwitchBatch k, Manufacturer m where m.id = k.manufacturer.id and m.name = :name")
    List<SwitchBatch> getAllSwitchBatchFromAManufacturer(@Param("name") String name);

    @Query("from  SwitchBatch k where " +
            "k.importedDate = :date ")
    List<SwitchBatch> getAllSwitchBatchFromADay(@Param("date") LocalDate date);

}
