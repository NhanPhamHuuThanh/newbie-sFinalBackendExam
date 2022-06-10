package com.axonactive.backEndFinalExam.api;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Caze;
import com.axonactive.backEndFinalExam.service.CaseService;
import com.axonactive.backEndFinalExam.service.dto.CaseDto;
import com.axonactive.backEndFinalExam.service.mapper.CaseMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CaseResource.PATH)
public class CaseResource {
    public static final String PATH = "/api/cases";
    @Autowired
    private CaseService caseService;

    @GetMapping
    public ResponseEntity<List<CaseDto>> getALL() {
        return ResponseEntity.ok(CaseMapper.INSTANCE.toDtos(caseService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Caze caze = caseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for case"));
        return ResponseEntity.ok(CaseMapper.INSTANCE.toDto(caze));
    }

    @PostMapping
    public ResponseEntity<CaseDto> createNewCase(@RequestBody Caze caze) {
        Caze createdCase = caseService.save(caze);
        return ResponseEntity.created(URI.create(CaseResource.PATH + "/" + createdCase.getId()))
                .body(CaseMapper.INSTANCE.toDto(createdCase));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody Caze caze) throws ResourceNotFoundException {
        Caze updateCaze = caseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for case"));
        updateCaze.setMaterial(caze.getMaterial());
        updateCaze.setColor(caze.getColor());
        updateCaze.setWeight(caze.getWeight());
        return ResponseEntity.ok(CaseMapper.INSTANCE.toDto(caseService.save(updateCaze)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Caze updateCaze = caseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for case"));
        caseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
