package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.Pcb;
import com.axonactive.backEndFinalExam.repository.PcbRepo;
import com.axonactive.backEndFinalExam.service.PcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcbServiceImpl implements PcbService {
    @Autowired
    private PcbRepo pcbRepo;

    @Override
    public List<Pcb> getAll() {
        return pcbRepo.findAll();
    }

    @Override
    public Optional<Pcb> findById(Integer id) {
        return pcbRepo.findById(id);
    }

    @Override
    public Pcb save(Pcb pcb) {
        return pcbRepo.save(pcb);
    }

    @Override
    public void deleteById(Integer id) {
        pcbRepo.deleteById(id);
    }
}
