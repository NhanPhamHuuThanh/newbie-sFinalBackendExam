package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.entity.SwitchSpring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchSpringRepo extends JpaRepository<SwitchSpring, Integer> {
    SwitchSpring findBySwitchBatchSwitchName(String name);

}
