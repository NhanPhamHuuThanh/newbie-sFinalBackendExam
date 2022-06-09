package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycapSetRepo extends JpaRepository<KeyCapSet,Integer> {
}
