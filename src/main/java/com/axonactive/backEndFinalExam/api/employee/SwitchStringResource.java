package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.entity.SwitchSpring;
import com.axonactive.backEndFinalExam.service.SwitchStringService;
import com.axonactive.backEndFinalExam.service.dto.SwitchStringDto;
import com.axonactive.backEndFinalExam.service.mapper.SwitchStringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SwitchStringResource.PATH)
public class SwitchStringResource {
    public static final String PATH = "/api/switchStrings";

    @Autowired
    private SwitchStringService switchStringService;

    @GetMapping
    public ResponseEntity<List<SwitchStringDto>> getALL(){
        return ResponseEntity.ok(SwitchStringMapper.INSTANCE.toDtos(switchStringService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwitchStringDto>  getById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        SwitchSpring switchString = switchStringService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchString"));
        return ResponseEntity.ok(SwitchStringMapper.INSTANCE.toDto(switchString));
    }

    @PostMapping
    public ResponseEntity<SwitchStringDto> createNewCase(@RequestBody SwitchSpring switchString){
        SwitchSpring switchStringCreated = switchStringService.save(switchString);
        return ResponseEntity.created(URI.create(SwitchStringResource.PATH+"/"+switchString.getId()))
                .body(SwitchStringMapper.INSTANCE.toDto(switchStringCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwitchStringDto> updateCase(@PathVariable(value = "id")Integer id ,@RequestBody SwitchSpring switchString) throws ResourceNotFoundException {
        SwitchSpring updateSwitchString = switchStringService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchString"));
        updateSwitchString.setAmountOfLiquidRecommended(switchString.getAmountOfLiquidRecommended());
        updateSwitchString.setSpringForce(switchString.getSpringForce());
        updateSwitchString.setSpringLength(switchString.getSpringLength());
        updateSwitchString.setSpringType(switchString.getSpringType());
        return ResponseEntity.ok(SwitchStringMapper.INSTANCE.toDto(switchStringService.save(updateSwitchString)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        SwitchSpring deleteSwitchString = switchStringService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id not found for switchString"));
        switchStringService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
