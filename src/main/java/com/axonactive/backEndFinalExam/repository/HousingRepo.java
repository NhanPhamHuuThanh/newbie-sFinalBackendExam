package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HousingRepo extends JpaRepository<Housing, Integer> {
}
