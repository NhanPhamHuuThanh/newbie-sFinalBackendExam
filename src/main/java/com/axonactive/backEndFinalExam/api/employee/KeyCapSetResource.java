package com.axonactive.backEndFinalExam.api.employee;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyCapSetRequest;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.PriceUpdateRecordService;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import com.axonactive.backEndFinalExam.service.mapper.KeycapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(KeyCapSetResource.PATH)
@PreAuthorize("hasRole('ADMIN')")
public class KeyCapSetResource {
    public static final String PATH = "/api/keyCapSets";
    @Autowired
    private KeycapSetService keyCapSetService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private PriceUpdateRecordService priceUpdateRecordService;


    @GetMapping
    public ResponseEntity<List<KeyCapSetDto>> getAll() {
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDtos(keyCapSetService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyCapSetDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyCapSet keyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDto(keyCapSet));
    }

    @PostMapping
    public ResponseEntity<KeyCapSetDto> createNewBatch(@RequestBody KeyCapSet keyCapSet) {
        KeyCapSet keyCapSetCreated = keyCapSetService.save(keyCapSet);
        return ResponseEntity.created(URI.create(KeyCapSetResource.PATH + "/" + keyCapSet.getId()))
                .body(KeycapMapper.INSTANCE.toDto(keyCapSetCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyCapSet> updateCase(@PathVariable(value = "id") Integer id, @RequestBody KeyCapSetDto keyCapSet) throws ResourceNotFoundException {
        KeyCapSet updateKeyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        updateKeyCapSet.setColor(keyCapSet.getColor());
        updateKeyCapSet.setImportedDate(keyCapSet.getImportedDate());
        updateKeyCapSet.setKeyCabPrintingTechnique(keyCapSet.getKeyCabPrintingTechnique());
        updateKeyCapSet.setKeycapProfile(keyCapSet.getKeycapProfile());
        updateKeyCapSet.setName(keyCapSet.getName());
        updateKeyCapSet.setManufacturer(manufacturerService.findByManufacturerNameLike(keyCapSet.getManufacturerName()));
        updateKeyCapSet.setMaterial(keyCapSet.getMaterial());
        updateKeyCapSet.setPrice(keyCapSet.getPrice());
        return ResponseEntity.ok(keyCapSetService.save(updateKeyCapSet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        KeyCapSet deleteKeyCapSet = keyCapSetService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for keyCapSet"));
        keyCapSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sets")
    public ResponseEntity<KeyCapSetDto> createWithExistingData(@RequestBody KeyCapSetRequest keyCapSetRequest) throws ResourceNotFoundException {
        KeyCapSet keyCapSetCreated = keyCapSetService.saveARequest(keyCapSetRequest);
        return ResponseEntity.created(URI.create(KeyCapSetResource.PATH + "/" + keyCapSetCreated.getId()))
                .body(KeycapMapper.INSTANCE.toDto(keyCapSetCreated));
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<KeyCapSetDto>> getKeyCapSetFromACertainDay(@PathVariable("date") String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDtos(keyCapSetService.getAllKeyCapSetWithImportedDate(date)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<KeyCapSetDto> getKeyCapSet(@PathVariable("name") String name) {
        return ResponseEntity.ok(keyCapSetService.getAllInStock(name));
    }

    @GetMapping("/short_in_stock")
    public ResponseEntity<List<KeyCapSetDto>> getAlMostOutOfStockKeyCapSet() throws ResourceNotFoundException {
        return ResponseEntity.ok(keyCapSetService.almostOutOfStockKeyCapSet());
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<KeyCapSetDto>> getKeyCapSetOnMonthAndYear(@PathVariable("year") int year,
                                                                         @PathVariable("month") int month) throws ResourceNotFoundException {
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDtos(keyCapSetService.keyCapSetBatchImportedDateWasInMonth(month, year)));
    }

    @GetMapping("/get-update/{name}")
    public ResponseEntity<List<PriceUpdateRecord>> getUpdateRecordOfADay(@PathVariable("name") String name,
                                                                      @RequestParam("date") String date) throws ResourceNotFoundException {
        LocalDate dateOfTheRecord = LocalDate.parse(date);
        List<PriceUpdateRecord> record = priceUpdateRecordService.findAllTheUpdateHistoryOfKeyCapSet(name, dateOfTheRecord);
        if (record.isEmpty())
            throw new ResourceNotFoundException("No Record have been found");
        else
            return ResponseEntity.ok(record);
    }

    @GetMapping("/manu/{name}")
    public ResponseEntity<List<KeyCapSetDto>> getKeyCapSetFromManu(@PathVariable("name") String name) throws ResourceNotFoundException {
        return ResponseEntity.ok(KeycapMapper.INSTANCE.toDtos((List<KeyCapSet>) keyCapSetService.getAllKeyCapSetFromAManufacturer(name)));
    }
}
