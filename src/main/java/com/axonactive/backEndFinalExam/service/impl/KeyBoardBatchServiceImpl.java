package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.KeyboardBatchRepo;
import com.axonactive.backEndFinalExam.service.KeyBoardBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class KeyBoardBatchServiceImpl implements KeyBoardBatchService {
    @Autowired
    private KeyboardBatchRepo keyboardBatchRepo;
    @Autowired
    private ManufacturerService manufacturerService;


    @Override
    public List<KeyboardBatch> getAll() {
        return keyboardBatchRepo.findAll();
    }

    @Override
    public Optional<KeyboardBatch> findById(Integer id) {
        return keyboardBatchRepo.findById(id);
    }

    @Override
    public KeyboardBatch save(KeyboardBatch keyboardBatch) {
        return keyboardBatchRepo.save(keyboardBatch);
    }

    @Override
    public void deleteById(Integer id) {
        keyboardBatchRepo.deleteById(id);
    }

    @Override
    public KeyboardBatchDto checkStockAvailability(String name) {
        KeyboardBatchDto keyboardSearchedDto = new KeyboardBatchDto();
        List<KeyboardBatch> keyboardList = keyboardBatchRepo.getAllInStock(name);
        keyboardSearchedDto.setQuantity(0);
        keyboardSearchedDto.setSoldUnits(0);
        Collections.sort(keyboardList, new Comparator<KeyboardBatch>() {
            @Override
            public int compare(KeyboardBatch o1, KeyboardBatch o2) {
                return (o2.getImportedDate().compareTo(o1.getImportedDate()));
            }
        });
        for (KeyboardBatch batch : keyboardList
        ) {
            keyboardSearchedDto.setQuantity(keyboardSearchedDto.getQuantity() + batch.getQuantity()- batch.getSoldUnits());
        }
        for (KeyboardBatch batch : keyboardBatchRepo.getSoldUnits(name)
        ) {
            keyboardSearchedDto.setSoldUnits(keyboardSearchedDto.getSoldUnits() + batch.getSoldUnits());
        }
        keyboardSearchedDto.setPricePerUnit(keyboardList.get(0).getPricePerUnit());
        if (manufacturerService.findByManufacturerNameLike(keyboardSearchedDto.getManufacturerName())==null)
            keyboardSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            keyboardSearchedDto.setManufacturerName(keyboardList.get(0).getManufacturer().getName());
        keyboardSearchedDto.setKeyCapName(keyboardList.get(0).getKeyCapName());
        keyboardSearchedDto.setSwitchName(keyboardList.get(0).getSwitchBatchName());
        keyboardSearchedDto.setKitBatchName(keyboardList.get(0).getKitBatchName());
        keyboardSearchedDto.setManufacturerName(keyboardList.get(0).getManufacturer().getName());
        keyboardSearchedDto.setInsuranceWarrantyMonth(keyboardList.get(0).getInsuranceWarrantyMonth());
        keyboardSearchedDto.setStatus(Status.INSTOCK);
        return keyboardSearchedDto;
    }
}
