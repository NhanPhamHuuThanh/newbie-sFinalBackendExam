package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.KitBatchRepo;
import com.axonactive.backEndFinalExam.repository.ManufacturerRepo;
import com.axonactive.backEndFinalExam.service.KitBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class KitBatchServiceImpl implements KitBatchService {
    @Autowired
    private KitBatchRepo kitBatchRepo;
    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public List<KitBatch> findAll() {
        return kitBatchRepo.findAll();
    }

    @Override
    public Optional<KitBatch> findById(Integer id) {
        return kitBatchRepo.findById(id);
    }

    @Override
    public KitBatch save(KitBatch kitBatch) {
        return kitBatchRepo.save(kitBatch);
    }

    @Override
    public void deleteById(Integer id) {
        kitBatchRepo.deleteById(id);
    }

    @Override
    public KitBatchDto checkStockAvailability(String name,String color) {
        KitBatchDto kitSearchedDto = new KitBatchDto();
        List<KitBatch> kitBatchList = kitBatchRepo.getAllInStock(name,color);
        kitSearchedDto.setQuantity(0);
        kitSearchedDto.setSoldUnits(0);
        Collections.sort(kitBatchList, new Comparator<KitBatch>() {
            @Override
            public int compare(KitBatch o1, KitBatch o2) {
                return  (o2.getImportedDate().compareTo(o1.getImportedDate()));
            }
        });
        for (KitBatch batch: kitBatchList
        ) {
            kitSearchedDto.setQuantity(kitSearchedDto.getQuantity()+batch.getQuantity()- batch.getSoldUnits());
        }
        for (KitBatch batch: kitBatchRepo.getSoldUnits(name,color)
        ) {
            kitSearchedDto.setSoldUnits(kitSearchedDto.getSoldUnits()+batch.getSoldUnits());
        }
        kitSearchedDto.setColor(color);
        kitSearchedDto.setLayOut(kitBatchList.get(0).getLayOut());
        if (manufacturerService.findByManufacturerNameLike(kitSearchedDto.getManufacturerName())==null)
            kitSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            kitSearchedDto.setManufacturerName(kitBatchList.get(0).getManufacturer().getName());
        kitSearchedDto.setPricePerUnit(kitBatchList.get(0).getPricePerUnit());
        kitSearchedDto.setWeight(kitBatchList.get(0).getWeight());
        kitSearchedDto.setModel(name);
        kitSearchedDto.setColor(color);
        kitSearchedDto.setStatus(Status.INSTOCK);
        return kitSearchedDto;
    }
}
