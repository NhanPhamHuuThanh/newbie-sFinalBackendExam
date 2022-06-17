package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.repository.ManufacturerRepo;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepo.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Integer id) {
        return manufacturerRepo.findById(id);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepo.save(manufacturer);
    }

    @Override
    public void deleteById(Integer id) {
    manufacturerRepo.deleteById(id);
    }

    @Override
    public Manufacturer findByManufacturerNameLike(String name) {
        return manufacturerRepo.findByNameLike(name);
    }


}
