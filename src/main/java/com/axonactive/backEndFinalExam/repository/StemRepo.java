package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Stem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StemRepo extends JpaRepository<Stem, Integer> {
}
