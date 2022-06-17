package com.axonactive.backEndFinalExam.api.employeeapi;


import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Pcb;
import com.axonactive.backEndFinalExam.service.PcbService;
import com.axonactive.backEndFinalExam.service.dto.PcbDto;
import com.axonactive.backEndFinalExam.service.mapper.PcbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PcbResource.PATH)
public class PcbResource {
    public static final String PATH = "/api/pcbs";
    @Autowired
    private PcbService pcbService;

    @GetMapping
    public ResponseEntity<List<PcbDto>> getALL() {
        return ResponseEntity.ok(PcbMapper.INSTANCE.toDtos(pcbService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PcbDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Pcb pcb = pcbService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for pcb"));
        return ResponseEntity.ok(PcbMapper.INSTANCE.toDto(pcb));
    }

    @PostMapping
    public ResponseEntity<PcbDto> createNewCase(@RequestBody Pcb pcb) {
        Pcb pcbCreated = pcbService.save(pcb);
        return ResponseEntity.created(URI.create(PcbResource.PATH + "/" + pcb.getId()))
                .body(PcbMapper.INSTANCE.toDto(pcbCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PcbDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody Pcb pcb) throws ResourceNotFoundException {
        Pcb updatePcb = pcbService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for pcb"));
        updatePcb.setMaterial(pcb.getMaterial());
        updatePcb.setPcbCut(pcb.getPcbCut());
        updatePcb.setPcbType(pcb.getPcbType());
        return ResponseEntity.ok(PcbMapper.INSTANCE.toDto(pcbService.save(updatePcb)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Pcb deletePcb = pcbService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for pcb"));
        pcbService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

