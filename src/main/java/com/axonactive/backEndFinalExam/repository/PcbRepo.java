package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Pcb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcbRepo extends JpaRepository<Pcb,Integer> {
}
