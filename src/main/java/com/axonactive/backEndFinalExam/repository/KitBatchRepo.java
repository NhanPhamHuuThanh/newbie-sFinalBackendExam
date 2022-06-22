package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.KitBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KitBatchRepo extends JpaRepository<KitBatch, Integer> {
    @Query(" from KitBatch where model = :name and status = 'INSTOCK' and color = :color")
    List<KitBatch> getAllInStock(@Param("name") String name,
                                 @Param("color")String color);

    @Query(" from KitBatch where model = :name and color = :color")
    List<KitBatch> getSoldUnits(@Param("name") String name,
                                @Param("color") String color
    );

    @Query(" from KitBatch where model = :name and color = :color")
    List<KitBatch> getAll(@Param("name") String name,
                                 @Param("color")String color);

    @Query("select k from KitBatch k, Manufacturer m where m.id = k.manufacturer.id and m.name = :name")
    List<KitBatch> getAllKitBatchFromAManufacturer(@Param("name") String name);

    @Query("from  KitBatch k where " +
            "k.importedDate = :date ")
    List<KitBatch> getAllKitBatchFromADay(@Param("date") LocalDate date);

}
