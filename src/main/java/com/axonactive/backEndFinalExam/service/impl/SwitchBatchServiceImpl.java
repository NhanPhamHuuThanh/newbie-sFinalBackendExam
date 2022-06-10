package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.*;
import com.axonactive.backEndFinalExam.repository.SwitchBatchRepo;
import com.axonactive.backEndFinalExam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwitchBatchServiceImpl implements SwitchBatchService {
    @Autowired
    private SwitchBatchRepo switchBatchRepo;
    @Autowired
    private StemService stemService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private HousingService housingService;
    @Autowired
    private SwitchStringService switchStringService;


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
    public SwitchBatch save(SwitchBatchRequest switchBatchRequest) {
        SwitchBatch switchBatch = new SwitchBatch();
        switchBatch.setSwitchType(switchBatchRequest.getSwitchType());
        switchBatch.setSwitchName(switchBatchRequest.getSwitchName());
        switchBatch.setQuantity(switchBatchRequest.getQuantity());
        switchBatch.setPricePerUnit(switchBatchRequest.getPricePerUnit());
        switchBatch.setAmountOfLubeForSwitch(switchBatchRequest.getAmountOfLubeForSwitch());
        switchBatch.setImportedDate(switchBatchRequest.getImportedDate());
        switchBatch.setStem(stemService.findById(switchBatchRequest.getStemId()).get());
        switchBatch.setSwitchString(switchStringService.findById(switchBatchRequest.getSwitchStringId()).get());
        switchBatch.setHousing(housingService.findById(switchBatchRequest.getHousingId()).get());
        switchBatch.setManufacturer(manufacturerService.findById(switchBatchRequest.getManufacturerId()).get());
        return switchBatchRepo.save(switchBatch);
    }

    @Override
    public void deleteById(Integer id) {
        switchBatchRepo.deleteById(id);
    }


}
