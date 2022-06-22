package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.entity.Stabilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StabilizerRepo extends JpaRepository<Stabilizer, Integer> {

    @Query("select s from KitBatch k ,Stabilizer s, StabAndKitBatch sk where k.id= sk.kitBatch.id and s.id = sk.stabilizer.id and k.model = :name")
    Stabilizer findStabilizerFromKitBatch(@Param("name") String name);


}
