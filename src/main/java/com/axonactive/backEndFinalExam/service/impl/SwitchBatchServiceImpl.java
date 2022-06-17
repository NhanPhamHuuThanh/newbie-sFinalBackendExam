package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.SwitchBatchRepo;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SwitchBatchServiceImpl implements SwitchBatchService {
    @Autowired
    private SwitchBatchRepo switchBatchRepo;
    @Autowired
    private ManufacturerService  manufacturerService;


    @Override
    public List<SwitchBatch> getAll() {
        return switchBatchRepo.findAll();
    }

    @Override
    public Optional<SwitchBatch> findById(Integer id) {
        return switchBatchRepo.findById(id);
    }

    @Override
    public SwitchBatch save(SwitchBatch switchBatch) {

        return switchBatchRepo.save(switchBatch);
    }

    @Override
    public void deleteById(Integer id) {
        switchBatchRepo.deleteById(id);
    }

    @Override
    public SwitchBatchDto checkStockAvailability(String name) {
        SwitchBatchDto switchSearchedDto = new SwitchBatchDto();
        List<SwitchBatch> switchBatchList = switchBatchRepo.getAllInstock(name);
        switchSearchedDto.setQuantity(0);
        switchSearchedDto.setSoldUnits(0);
        Collections.sort(switchBatchList, new Comparator<SwitchBatch>() {
            @Override
            public int compare(SwitchBatch o1, SwitchBatch o2) {
                return (o2.getImportedDate().compareTo(o1.getImportedDate()));
            }
        });
        for (SwitchBatch batch : switchBatchList
        ) {
            switchSearchedDto.setQuantity(switchSearchedDto.getQuantity() + batch.getQuantity()- batch.getSoldUnits());
        }
        for (SwitchBatch batch : switchBatchRepo.getSoldUnits(name)
        ) {
            switchSearchedDto.setSoldUnits(switchSearchedDto.getSoldUnits() + batch.getSoldUnits());
        }
        switchSearchedDto.setPricePerUnit(switchBatchList.get(0).getPricePerUnit());
        if (manufacturerService.findByManufacturerNameLike(switchSearchedDto.getManufacturerName())==null)
            switchSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            switchSearchedDto.setManufacturerName(switchBatchList.get(0).getManufacturer().getName());

        switchSearchedDto.setSwitchName(switchBatchList.get(0).getSwitchName());
        switchSearchedDto.setSwitchType(switchBatchList.get(0).getSwitchType());
        switchSearchedDto.setStatus(Status.INSTOCK);

            return switchSearchedDto;
    }

}



