package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.User;
import com.axonactive.backEndFinalExam.repository.UserRepo;
import com.axonactive.backEndFinalExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo employeeRepo;

    @Override
    public List<User> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return employeeRepo.findById(id);
    }

    @Override
    public User save(User caseInput) {
        return employeeRepo.save(caseInput);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepo.deleteById(id);
    }
}
