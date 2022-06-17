package com.axonactive.backEndFinalExam.repository;


import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PriceUpdateRecordRepo extends JpaRepository<PriceUpdateRecord,Integer> {
    @Query("select m from PriceUpdateRecord m, KeyCapSet k where k.name = :name " +
            "and m.updatePriceDate = :date and k.id =m.itemId and m.itemType = 'KeyCapSet' ")
    List <PriceUpdateRecord> findAllTheUpdateHistoryOfKeyCapSet(@Param("name") String name,
                                                     @Param("date")  LocalDate date);

    @Query("select m from PriceUpdateRecord m, SwitchBatch k where k.switchName = :name " +
            "and m.updatePriceDate = :date and k.id =m.itemId and m.itemType = 'SwitchBatch' ")
    List <PriceUpdateRecord> findAllTheUpdateHistoryOfSwitchBatch(@Param("name") String name,
                                                     @Param("date")  LocalDate date);

    @Query("select m from PriceUpdateRecord m, KitBatch k where k.model = :name " +
            "and m.updatePriceDate = :date and k.id =m.itemId and m.itemType = 'KitBatch' ")
    List <PriceUpdateRecord> findAllTheUpdateHistoryOfKitBatch(@Param("name") String name,
                                                     @Param("date")  LocalDate date);

    @Query("select m from PriceUpdateRecord m, KeyboardBatch k where k.name = :name " +
            "and m.updatePriceDate = :date and k.id =m.itemId and m.itemType = 'KeyboardBatch' ")
    List <PriceUpdateRecord> findAllTheUpdateHistoryOfKeyboardBatch(@Param("name") String name,
                                                     @Param("date")  LocalDate date);
}
