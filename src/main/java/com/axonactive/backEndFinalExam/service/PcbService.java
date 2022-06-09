package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.entity.Pcb;

import java.util.List;
import java.util.Optional;

public interface PcbService {
    List<Pcb> getAll();

    Optional<Pcb> findById(Integer id);

    Pcb save(Pcb pcb);

    void deleteById(Integer id);
}
