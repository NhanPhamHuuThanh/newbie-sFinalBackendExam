package com.axonactive.backEndFinalExam.api;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import com.axonactive.backEndFinalExam.service.mapper.KeycapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(KeyCapSetResource.PATH)
public class KeyCapSetResource {
    public static final String PATH = "/api/keyCapSets";
    @Autowired
    private KeycapSetService keyCapSetService;

    @GetMapping
    public ResponseEntity<List<KeyCapSetDto>> getALL() {
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDtos(keyCapSetService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyCapSetDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyCapSet keyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDto(keyCapSet));
    }

    @PostMapping
    public ResponseEntity<KeyCapSetDto> createNewCase(@RequestBody KeyCapSet keyCapSet) {
        KeyCapSet keyCapSetCreated = keyCapSetService.save(keyCapSet);
        return ResponseEntity.created(URI.create(CaseResource.PATH + "/" + keyCapSet.getId()))
                .body(KeycapMapper.INSTANCE.toDto(keyCapSetCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyCapSetDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody KeyCapSet keyCapSet) throws ResourceNotFoundException {
        KeyCapSet updateKeyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        updateKeyCapSet.setColor(keyCapSet.getColor());
        updateKeyCapSet.setImportedDate(keyCapSet.getImportedDate());
        updateKeyCapSet.setKeyCabPrintingTechnique(keyCapSet.getKeyCabPrintingTechnique());
        updateKeyCapSet.setKeycapProfile(keyCapSet.getKeycapProfile());
        updateKeyCapSet.setManufacturer(keyCapSet.getManufacturer());
        updateKeyCapSet.setMaterial(keyCapSet.getMaterial());
        updateKeyCapSet.setPrice(keyCapSet.getPrice());
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDto(keyCapSetService.save(updateKeyCapSet)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyCapSet deleteKeyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        keyCapSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
