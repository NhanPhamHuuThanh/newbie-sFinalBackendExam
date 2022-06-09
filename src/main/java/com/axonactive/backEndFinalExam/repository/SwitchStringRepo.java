package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.SwitchString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchStringRepo extends JpaRepository<SwitchString,Integer> {
}
