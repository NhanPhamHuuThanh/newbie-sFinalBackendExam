package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.CustomKeyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomKeyboardRepo extends JpaRepository<CustomKeyboard, Integer> {
}
