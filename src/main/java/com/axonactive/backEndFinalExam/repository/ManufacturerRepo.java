package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo  extends JpaRepository<Manufacturer,Integer> {
}
