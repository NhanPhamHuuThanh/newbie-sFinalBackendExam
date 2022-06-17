package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Customer;
import com.axonactive.backEndFinalExam.repository.CustomerRepo;
import com.axonactive.backEndFinalExam.service.CustomerService;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SwitchBatchService switchBatchService;

    @Override
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepo.findById(id);
    }

    @Override
    public Customer save(Customer caseInput) {
        return customerRepo.save(caseInput);
    }

    @Override
    public void deleteById(Integer id) {
        customerRepo.deleteById(id);
    }

}
