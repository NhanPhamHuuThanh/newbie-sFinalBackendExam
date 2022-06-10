package com.axonactive.backEndFinalExam.api;


import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Stabilizer;
import com.axonactive.backEndFinalExam.service.StabilizerService;
import com.axonactive.backEndFinalExam.service.dto.StabilizerDto;
import com.axonactive.backEndFinalExam.service.mapper.StabilizerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StabilizerResource.PATH)
public class StabilizerResource {
    public static final String PATH = "/api/stabilizers";
    @Autowired
    private StabilizerService stabilizerService;

    @GetMapping
    public ResponseEntity<List<StabilizerDto>> getALL(){
        return ResponseEntity.ok(StabilizerMapper.INSTANCE.toDtos(stabilizerService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StabilizerDto>  getById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Stabilizer stabilizer = stabilizerService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stabilizer"));
        return ResponseEntity.ok(StabilizerMapper.INSTANCE.toDto(stabilizer));
    }

    @PostMapping
    public ResponseEntity<StabilizerDto> createNewCase(@RequestBody Stabilizer stabilizer){
        Stabilizer stabilizerCreated = stabilizerService.save(stabilizer);
        return ResponseEntity.created(URI.create(CaseResource.PATH+"/"+stabilizer.getId()))
                .body(StabilizerMapper.INSTANCE.toDto(stabilizerCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StabilizerDto> updateCase(@PathVariable(value = "id")Integer id ,@RequestBody Stabilizer stabilizer) throws ResourceNotFoundException {
        Stabilizer updateStabilizer = stabilizerService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stabilizer"));
        updateStabilizer.setMaterial(stabilizer.getMaterial());
        updateStabilizer.setStabStatus(stabilizer.getStabStatus());
        updateStabilizer.setLength(stabilizer.getLength());
        return ResponseEntity.ok(StabilizerMapper.INSTANCE.toDto(stabilizerService.save(updateStabilizer)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Stabilizer deleteStabilizer = stabilizerService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stabilizer"));
        stabilizerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
