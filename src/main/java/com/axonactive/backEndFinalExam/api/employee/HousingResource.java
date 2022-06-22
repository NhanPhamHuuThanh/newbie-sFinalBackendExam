package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.service.HousingService;
import com.axonactive.backEndFinalExam.service.dto.HousingDto;
import com.axonactive.backEndFinalExam.service.mapper.HousingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(HousingResource.PATH)
public class HousingResource {
    public static final String PATH = "/api/housings";
    @Autowired
    private HousingService housingService;

    @GetMapping
    public ResponseEntity<List<HousingDto>> getALL() {
        return ResponseEntity.ok(HousingMapper.INSTANCE.toDtos(housingService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousingDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Housing housing = housingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for housing"));
        return ResponseEntity.ok(HousingMapper.INSTANCE.toDto(housing));
    }

    @PostMapping
    public ResponseEntity<HousingDto> createNewCase(@RequestBody Housing housing) {
        Housing housingCreated = housingService.save(housing);
        return ResponseEntity.created(URI.create(HousingResource.PATH + "/" + housing.getId()))
                .body(HousingMapper.INSTANCE.toDto(housingCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousingDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody Housing housing) throws ResourceNotFoundException {
        Housing updateHousing = housingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for housing"));
        updateHousing.setMaterial(housing.getMaterial());
        updateHousing.setColor(housing.getColor());
        updateHousing.setMountType(housing.getMountType());
        return ResponseEntity.ok(HousingMapper.INSTANCE.toDto(housingService.save(updateHousing)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Housing deleteHousing = housingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for housing"));
        housingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
