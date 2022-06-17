package com.axonactive.backEndFinalExam.api.employeeapi;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Stem;
import com.axonactive.backEndFinalExam.service.StemService;
import com.axonactive.backEndFinalExam.service.dto.StemDto;
import com.axonactive.backEndFinalExam.service.mapper.StemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StemResource.PATH)
public class StemResource {
    public static final String PATH = "/api/stems";
    @Autowired
    private StemService stemService;

    @GetMapping
    public ResponseEntity<List<StemDto>> getALL(){
        return ResponseEntity.ok(StemMapper.INSTANCE.toDtos(stemService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StemDto>  getById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Stem stem = stemService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stem"));
        return ResponseEntity.ok(StemMapper.INSTANCE.toDto(stem));
    }

    @PostMapping
    public ResponseEntity<StemDto> createNewCase(@RequestBody Stem stem){
        Stem stemCreated = stemService.save(stem);
        return ResponseEntity.created(URI.create(StemResource.PATH+"/"+stem.getId()))
                .body(StemMapper.INSTANCE.toDto(stemCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StemDto> updateCase(@PathVariable(value = "id")Integer id ,@RequestBody Stem stem) throws ResourceNotFoundException {
        Stem updateStem = stemService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stem"));
        updateStem.setMaterial(stem.getMaterial());
        updateStem.setStemColor(stem.getStemColor());
        updateStem.setStemHeight(stem.getStemHeight());
        return ResponseEntity.ok(StemMapper.INSTANCE.toDto(stemService.save(updateStem)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Stem deleteStem = stemService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for stem"));
        stemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
