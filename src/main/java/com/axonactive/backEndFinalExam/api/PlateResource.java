package com.axonactive.backEndFinalExam.api;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Plate;
import com.axonactive.backEndFinalExam.service.PlateService;
import com.axonactive.backEndFinalExam.service.dto.PLateDto;
import com.axonactive.backEndFinalExam.service.mapper.PlateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PlateResource.PATH)
public class PlateResource {
    public static final String PATH = "/api/plates";
    @Autowired
    private PlateService plateService;

    @GetMapping
    public ResponseEntity<List<PLateDto>> getALL() {
        return ResponseEntity.ok(PlateMapper.INSTANCE.toDtos(plateService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PLateDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Plate plate = plateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for plate"));
        return ResponseEntity.ok(PlateMapper.INSTANCE.toDto(plate));
    }

    @PostMapping
    public ResponseEntity<PLateDto> createNewCase(@RequestBody Plate plate) {
        Plate plateCreated = plateService.save(plate);
        return ResponseEntity.created(URI.create(CaseResource.PATH + "/" + plate.getId()))
                .body(PlateMapper.INSTANCE.toDto(plateCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PLateDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody Plate plate) throws ResourceNotFoundException {
        Plate updatePlate = plateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for plate"));
        updatePlate.setMaterial(plate.getMaterial());
        updatePlate.setMountType(plate.getMountType());
        return ResponseEntity.ok(PlateMapper.INSTANCE.toDto(plateService.save(updatePlate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Plate deletePlate = plateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for plate"));
        plateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

