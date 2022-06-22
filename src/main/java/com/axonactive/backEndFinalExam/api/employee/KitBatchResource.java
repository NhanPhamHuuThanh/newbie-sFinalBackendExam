package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KitBatchRequest;
import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.service.KitBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import com.axonactive.backEndFinalExam.service.mapper.KitBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(KitBatchResource.PATH)
public class KitBatchResource {
    public static final String PATH = "/api/kitbatches";
    @Autowired
    private KitBatchService kitBatchService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<KitBatchDto>> getALL() {
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDtos(kitBatchService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitBatchDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KitBatch kitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDto(kitBatch));
    }

    @PostMapping
    public ResponseEntity<KitBatchDto> createNewCase(@RequestBody KitBatch kitBatch) {
        KitBatch kitBatchCreated = kitBatchService.save(kitBatch);
        return ResponseEntity.created(URI.create(KitBatchResource.PATH + "/" + kitBatch.getId()))
                .body(KitBatchMapper.INSTANCE.toDto(kitBatchCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitBatch> updateCase(@PathVariable(value = "id") Integer id, @RequestBody KitBatchDto kitBatch) throws ResourceNotFoundException {
        KitBatch updateKitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        updateKitBatch.setImportedDate(kitBatch.getImportedDate());
        updateKitBatch.setLayOut(kitBatch.getLayOut());
        updateKitBatch.setModel(kitBatch.getModel());
        updateKitBatch.setManufacturedDate(kitBatch.getManufacturedDate());
        updateKitBatch.setQuantity(kitBatch.getQuantity());
        updateKitBatch.setPricePerUnit(kitBatch.getPricePerUnit());
        updateKitBatch.setStatus(kitBatch.getStatus());
        updateKitBatch.setManufacturer(manufacturerService
                .findByManufacturerNameLike(kitBatch.getManufacturerName()));
        return ResponseEntity.ok(kitBatchService.save(updateKitBatch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KitBatch deleteKitBatch = kitBatchService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for kitBatch"));
        kitBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/sets")
    public ResponseEntity<KitBatchDto> createWithExistingData(@RequestBody KitBatchRequest kitBatchRequest) throws ResourceNotFoundException {
        KitBatch kitBatchCreated = kitBatchService.createPriceUpdateRecord(kitBatchRequest);
        return ResponseEntity.created(URI.create(KitBatchResource.PATH + "/" + kitBatchCreated.getId()))
                .body(KitBatchMapper.INSTANCE.toDto(kitBatchCreated));
    }
    @GetMapping("/{date}")
    public ResponseEntity<List<KitBatchDto>> getKitBatchFromACertainDay(@PathVariable("date") LocalDate date){
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDtos(kitBatchService.listOfAllKitBatchOnAGivenDay(date)));
    }

    @GetMapping("/{name}/{color}")
    public ResponseEntity<KitBatchDto> getKitBatch(@PathVariable("name")String name,
                                                         @PathVariable("color") String color){
        return ResponseEntity.ok(kitBatchService.checkStockAvailability(name,color));
    }

    @GetMapping("/short_in_stock")
    public ResponseEntity<List<KitBatchDto>> getAlMostOutOfStockKitBatch() throws ResourceNotFoundException {
        return ResponseEntity.ok(kitBatchService.almostOutOfStockKitBatch());
    }
    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<KitBatchDto>> getKitBatchOnMonthAndYear(@PathVariable("year") int year,
                                                         @PathVariable("month") int month) throws ResourceNotFoundException {
        return ResponseEntity.ok(KitBatchMapper.INSTANCE.toDtos(kitBatchService.kitBatchImportedDateWasInMonth(month, year)));
    }


}
