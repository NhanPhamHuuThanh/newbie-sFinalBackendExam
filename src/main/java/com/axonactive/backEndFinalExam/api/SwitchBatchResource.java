package com.axonactive.backEndFinalExam.api;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.SwitchBatch;
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
    public static final String PATH = "/api/switchBatchs";

    @Autowired
    private SwitchBatchService switchBatchService;

    @GetMapping
    public ResponseEntity<List<SwitchBatchDto>> getALL(){
        return ResponseEntity.ok(SwitchBatchMapper.INSTANCE.toDtos(switchBatchService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwitchBatchDto>  getById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        SwitchBatch switchBatch = switchBatchService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchBatch"));
        return ResponseEntity.ok(SwitchBatchMapper.INSTANCE.toDto(switchBatch));
    }

    @PostMapping
    public ResponseEntity<SwitchBatchDto> createNewCaseWithExistingData(@RequestBody SwitchBatchRequest switchBatchRequest){
        return ResponseEntity.created(URI.create(CaseResource.PATH+"/"+switchBatchRequest.getId()))
                .body(SwitchBatchMapper.INSTANCE.toDto(switchBatchService.save(switchBatchRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwitchBatchDto> updateCaseWith(@PathVariable(value = "id")Integer id ,@RequestBody SwitchBatch switchBatch) throws ResourceNotFoundException {
        SwitchBatchDto updateSwitchBatch = SwitchBatchMapper.INSTANCE.toDto(switchBatchService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchBatch")));


        return ResponseEntity.ok(switchBatchService.save(updateSwitchBatch)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        SwitchBatch deleteSwitchBatch = switchBatchService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchBatch"));
        switchBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

