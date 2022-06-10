package com.axonactive.backEndFinalExam.api;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.ManufacturerDto;
import com.axonactive.backEndFinalExam.service.mapper.ManufacturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ManufacturerResource.PATH)
public class ManufacturerResource {
    public static final String PATH = "/api/manufacturers";
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> getALL() {
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.toDtos(manufacturerService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Manufacturer manufacturer = manufacturerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for manufacturer"));
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.toDto(manufacturer));
    }

    @PostMapping
    public ResponseEntity<ManufacturerDto> createNewCase(@RequestBody Manufacturer manufacturer) {
        Manufacturer manufacturerCreated = manufacturerService.save(manufacturer);
        return ResponseEntity.created(URI.create(CaseResource.PATH + "/" + manufacturer.getId()))
                .body(ManufacturerMapper.INSTANCE.toDto(manufacturerCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody Manufacturer manufacturer) throws ResourceNotFoundException {
        Manufacturer updateManufacturer = manufacturerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for manufacturer"));
        updateManufacturer.setEmail(manufacturer.getEmail());
        updateManufacturer.setLocation(manufacturer.getLocation());
        updateManufacturer.setName(manufacturer.getName());
        updateManufacturer.setPhoneNumber(manufacturer.getPhoneNumber());
        return ResponseEntity.ok(ManufacturerMapper.INSTANCE.toDto(manufacturerService.save(updateManufacturer)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Manufacturer deleteManufacturer = manufacturerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for manufacturer"));
        manufacturerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

