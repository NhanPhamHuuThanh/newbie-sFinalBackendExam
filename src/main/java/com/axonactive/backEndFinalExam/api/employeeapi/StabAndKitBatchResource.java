package com.axonactive.backEndFinalExam.api.employeeapi;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;
import com.axonactive.backEndFinalExam.service.KeyBoardBatchService;
import com.axonactive.backEndFinalExam.service.KitBatchService;
import com.axonactive.backEndFinalExam.service.StabAndKitBatchService;
import com.axonactive.backEndFinalExam.service.StabilizerService;
import com.axonactive.backEndFinalExam.service.dto.StabAndKitBatchDto;
import com.axonactive.backEndFinalExam.service.mapper.StabAndKitBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StabAndKitBatchResource.PATH)
public class StabAndKitBatchResource {
    public static final String PATH = "/api/stabAndKitBatchs";
    @Autowired
    private StabAndKitBatchService stabAndKitBatchService;
    @Autowired
    private KeyBoardBatchService keyBoardBatchService;
    @Autowired
    private StabilizerService stabilizerService;
    @Autowired
    private KitBatchService kitBatchService;


    @GetMapping
    public ResponseEntity<List<StabAndKitBatchDto>> getALL() {
        return ResponseEntity.ok(StabAndKitBatchMapper.INSTANCE.toDtos(stabAndKitBatchService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StabAndKitBatchDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        StabAndKitBatch stabAndKitBatch = stabAndKitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for stabAndKitBatch"));
        return ResponseEntity.ok(StabAndKitBatchMapper.INSTANCE.toDto(stabAndKitBatch));
    }

    @PostMapping
    public ResponseEntity<StabAndKitBatchDto> createNewCase(@RequestBody StabAndKitBatch stabAndKitBatch) {
        StabAndKitBatch stabAndKitBatchCreated = stabAndKitBatchService.save(stabAndKitBatch);
        return ResponseEntity.created(URI.create(StabAndKitBatchResource.PATH + "/" + stabAndKitBatch.getId()))
                .body(StabAndKitBatchMapper.INSTANCE.toDto(stabAndKitBatchCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StabAndKitBatch> updateCase(@PathVariable(value = "id") Integer id, @RequestBody StabAndKitBatchDto stabAndKitBatch) throws ResourceNotFoundException {
        StabAndKitBatch updateStabAndKitBatch = stabAndKitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for stabAndKitBatch "));
        updateStabAndKitBatch.setKitBatch(
                kitBatchService.findById(stabAndKitBatch.getKitBatchId())
                        .orElseThrow(() -> new ResourceNotFoundException("Id not found for Kit batch ")));
        updateStabAndKitBatch.setStabilizer(
                stabilizerService.findById(stabAndKitBatch.getStabilizerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Id not found for stabilizer ")));

        return ResponseEntity.ok(stabAndKitBatchService.save(updateStabAndKitBatch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        StabAndKitBatch deleteStabAndKitBatch = stabAndKitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for stabAndKitBatch"));
        stabAndKitBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
