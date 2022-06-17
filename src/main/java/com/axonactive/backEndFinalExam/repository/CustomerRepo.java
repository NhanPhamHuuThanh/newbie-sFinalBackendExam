package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
