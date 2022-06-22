package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.service.KeyBoardBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.PriceUpdateRecordService;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;
import com.axonactive.backEndFinalExam.service.mapper.KeyboardBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(KeyboardBatchResource.PATH)
public class KeyboardBatchResource {
    public static final String PATH = "/api/keyboardBatches";

    @Autowired
    private KeyBoardBatchService keyboardBatchService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private PriceUpdateRecordService priceUpdateRecordService;

    @GetMapping
    public ResponseEntity<List<KeyboardBatchDto>> getALL() {
        return ResponseEntity.ok(KeyboardBatchMapper.INSTANCE.toDtos(keyboardBatchService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyboardBatchDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyboardBatch keyboardBatch = keyboardBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyboardBatch"));
        return ResponseEntity.ok(KeyboardBatchMapper.INSTANCE.toDto(keyboardBatch));
    }

    @PostMapping
    public ResponseEntity<KeyboardBatchDto> createNewCaseWithExistingData(@RequestBody KeyboardBatch keyboardBatch) {
        KeyboardBatchDto keyboardBatchDto = KeyboardBatchMapper.INSTANCE.toDto(keyboardBatchService.save(keyboardBatch));


        return ResponseEntity.created(URI.create(KeyboardBatchResource.PATH + "/" + keyboardBatch.getId()))
                .body(keyboardBatchDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyboardBatch> updateCaseWith(@PathVariable(value = "id")Integer id ,@RequestBody KeyboardBatchDto keyboardBatch) throws ResourceNotFoundException {
        KeyboardBatch updateKeyboardBatch = keyboardBatchService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for keyboardBatch"));
        updateKeyboardBatch.setInsuranceWarrantyMonth(keyboardBatch.getInsuranceWarrantyMonth());
        updateKeyboardBatch.setImportedDate(keyboardBatch.getImportedDate());
        updateKeyboardBatch.setQuantity(keyboardBatch.getQuantity());
        updateKeyboardBatch.setStatus(keyboardBatch.getStatus());
        updateKeyboardBatch.setManufacturer(manufacturerService.findByManufacturerNameLike(keyboardBatch.getManufacturerName()));
        updateKeyboardBatch.setPricePerUnit(keyboardBatch.getPricePerUnit());
        updateKeyboardBatch.setName(keyboardBatch.getName());
        return ResponseEntity.ok(keyboardBatchService.save(updateKeyboardBatch));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyboardBatch deleteKeyboardBatch = keyboardBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyboardBatch"));
        keyboardBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

