package com.axonactive.backEndFinalExam.api.employeeapi;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.service.KitBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import com.axonactive.backEndFinalExam.service.mapper.KitBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(KitBatchResource.PATH)
public class KitBatchResource {
    public static final String PATH = "/api/kitBatches";
    @Autowired
    private KitBatchService kitBatchService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<KitBatchDto>> getALL() {
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDtos(kitBatchService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitBatchDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KitBatch kitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDto(kitBatch));
    }

    @PostMapping
    public ResponseEntity<KitBatchDto> createNewCase(@RequestBody KitBatch kitBatch) {
        KitBatch kitBatchCreated = kitBatchService.save(kitBatch);
        return ResponseEntity.created(URI.create(KitBatchResource.PATH + "/" + kitBatch.getId()))
                .body(KitBatchMapper.INSTANCE.toDto(kitBatchCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitBatch> updateCase(@PathVariable(value = "id") Integer id, @RequestBody KitBatchDto kitBatch) throws ResourceNotFoundException {
        KitBatch updateKitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        updateKitBatch.setImportedDate(kitBatch.getImportedDate());
        updateKitBatch.setLayOut(kitBatch.getLayOut());
        updateKitBatch.setModel(kitBatch.getModel());
        updateKitBatch.setManufacturedDate(kitBatch.getManufacturedDate());
        updateKitBatch.setQuantity(kitBatch.getQuantity());
        updateKitBatch.setPricePerUnit(kitBatch.getPricePerUnit());
        updateKitBatch.setStatus(kitBatch.getStatus());
        updateKitBatch.setManufacturer(manufacturerService
                .findByManufacturerNameLike(kitBatch.getManufacturerName()));
        return ResponseEntity.ok(kitBatchService.save(updateKitBatch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KitBatch deleteKitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        kitBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
