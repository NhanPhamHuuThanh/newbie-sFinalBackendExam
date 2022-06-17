package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepo extends JpaRepository<Plate, Integer> {
}
