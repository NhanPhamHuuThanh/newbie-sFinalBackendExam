package com.axonactive.backEndFinalExam.service;


import com.axonactive.backEndFinalExam.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> getAll();

    Optional<Manufacturer> findById(Integer id);

    Manufacturer save(Manufacturer manufacturer);

    void deleteById(Integer id);
}
