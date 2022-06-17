package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();

    Optional<Customer> findById(Integer id);

    Customer save(Customer caseInput);

    void deleteById(Integer id);


}
