package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyCapSetRequest;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.KeycapSetRepo;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class KeycabSetServiceImpl implements KeycapSetService {
    @Autowired
    private KeycapSetRepo keycapSetRepo;
    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public List<KeyCapSet> getAll() {
        return keycapSetRepo.findAll();
    }

    @Override
    public Optional<KeyCapSet> findById(Integer id) {
        return keycapSetRepo.findById(id);
    }

    @Override
    public KeyCapSet save(KeyCapSet keyCapSet) {
        return keycapSetRepo.save(keyCapSet);
    }

    @Override
    public void deleteById(Integer id) {
        keycapSetRepo.deleteById(id);
    }

    @Override
    public KeyCapSetDto getAllInStock(String name) {
        KeyCapSetDto keyCapSearchDto = new KeyCapSetDto();
        List<KeyCapSet> keyCapList = keycapSetRepo.getAllInStock(name);
        keyCapSearchDto.setQuantity(0);
        keyCapSearchDto.setSoldUnits(0);
        Collections.sort(keyCapList, new Comparator<KeyCapSet>() {
            @Override
            public int compare(KeyCapSet o1, KeyCapSet o2) {
                return (o2.getImportedDate().compareTo(o1.getImportedDate()));
            }
        });
        for (KeyCapSet batch : keyCapList
        ) {
            keyCapSearchDto.setQuantity(batch.getQuantity()- batch.getSoldUnits()+keyCapSearchDto.getQuantity());
        }
        for (KeyCapSet batch : keycapSetRepo.getSoldUnits(name)
        ) {
            keyCapSearchDto.setSoldUnits(keyCapSearchDto.getSoldUnits() + batch.getSoldUnits());
        }
        if (manufacturerService.findByManufacturerNameLike(keyCapSearchDto.getManufacturerName())==null)
        keyCapSearchDto.setManufacturerName("Not Found Manufacturer");
        else
            keyCapSearchDto.setManufacturerName(keyCapList.get(0).getColor());
        keyCapSearchDto.setColor(keyCapList.get(0).getColor());
        keyCapSearchDto.setKeycapProfile(keyCapList.get(0).getKeycapProfile());
        keyCapSearchDto.setKeyCabPrintingTechnique(keyCapList.get(0).getKeyCabPrintingTechnique());
        keyCapSearchDto.setName(keyCapList.get(0).getName());
        keyCapSearchDto.setMaterial(keyCapList.get(0).getMaterial());
        keyCapSearchDto.setPrice(keyCapList.get(0).getPrice());
        keyCapSearchDto.setStatus(Status.INSTOCK);

        return keyCapSearchDto;
    }

    @Override
    public List<KeyCapSet> getAllKeyCapSetWithNameAndImportedDate(String name, LocalDate date) {
        return keycapSetRepo.getAllKeyCapSetFromADayWithGivenName(name,date);
    }

    @Override
    public List<KeyCapSet> getAllKeyCapSetWithImportedDate(LocalDate date) {
        return keycapSetRepo.getAllKeyCapSetFromADay(date);
    }

    @Override
    public KeyCapSetDto saveARequest(KeyCapSetRequest keyCapSetRequest) {
        KeyCapSet keyCapSet = new KeyCapSet();

        keyCapSet.setManufacturer(manufacturerService.findByManufacturerNameLike(keyCapSetRequest.getManufacturerName()));


        return null;
    }

}
