package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> findById(Integer id);

    User save(User caseInput);

    void deleteById(Integer id);
}
