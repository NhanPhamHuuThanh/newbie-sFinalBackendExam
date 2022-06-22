package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import com.axonactive.backEndFinalExam.service.mapper.SwitchBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SwitchBatchResource.PATH)
public class SwitchBatchResource {
    public static final String PATH = "/api/switchBatches";

    @Autowired
    private SwitchBatchService switchBatchService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<SwitchBatchDto>> getALL() {
        return ResponseEntity.ok(SwitchBatchMapper.INSTANCE.toDtos(switchBatchService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwitchBatchDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        SwitchBatch switchBatch = switchBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for switchBatch"));
        return ResponseEntity.ok(SwitchBatchMapper.INSTANCE.toDto(switchBatch));
    }

    @PostMapping
    public ResponseEntity<SwitchBatchDto> createNewCaseWithExistingData(@RequestBody SwitchBatch switchBatch) {
        SwitchBatchDto switchBatchDto = SwitchBatchMapper.INSTANCE.toDto(switchBatchService.save(switchBatch));
        return ResponseEntity.created(URI.create(SwitchBatchResource.PATH + "/" + switchBatch.getId()))
                .body(switchBatchDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwitchBatch> updateCaseWith(@PathVariable(value = "id") Integer id, @RequestBody SwitchBatchDto switchBatch) throws ResourceNotFoundException {
        SwitchBatch updateSwitchBatch = switchBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for switchBatch"));
        updateSwitchBatch.setSwitchName(switchBatch.getSwitchName());
        updateSwitchBatch.setSwitchType(switchBatch.getSwitchType());
        updateSwitchBatch.setImportedDate(switchBatch.getImportedDate());
        updateSwitchBatch.setPricePerUnit(switchBatch.getPricePerUnit());
        updateSwitchBatch.setQuantity(switchBatch.getQuantity());
        updateSwitchBatch.setManufacturer(manufacturerService
                .findByManufacturerNameLike(switchBatch.getManufacturerName()));
        updateSwitchBatch.setStatus(switchBatch.getStatus());
        return ResponseEntity.ok(switchBatchService.save(updateSwitchBatch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        SwitchBatch deleteSwitchBatch = switchBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for switchBatch"));
        switchBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

