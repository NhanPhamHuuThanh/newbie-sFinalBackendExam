package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Caze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepo extends JpaRepository<Caze,Integer> {
}
