package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.api.request.CustomKeyboardRequest;
import com.axonactive.backEndFinalExam.entity.CustomKeyboard;
import com.axonactive.backEndFinalExam.repository.CustomKeyboardRepo;
import com.axonactive.backEndFinalExam.service.*;
import com.axonactive.backEndFinalExam.service.dto.CustomKeyboardDto;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomKeyboardServiceImpl implements CustomKeyboardService {

    @Autowired
    private CustomKeyboardRepo customKeyboardRepo;
    @Autowired
    private SwitchBatchService switchBatchService;
    @Autowired
    private KitBatchService kitBatchService;
    @Autowired
    private KeycapSetService keycapSetService;

    @Override
    public List<CustomKeyboard> getAll() {
        return customKeyboardRepo.findAll();
    }

    @Override
    public Optional<CustomKeyboard> findById(Integer id) {
        return customKeyboardRepo.findById(id);
    }

    @Override
    public CustomKeyboard save(CustomKeyboard caseInput) {
        return customKeyboardRepo.save(caseInput);
    }

    @Override
    public void deleteById(Integer id) {
        customKeyboardRepo.deleteById(id);
    }

    @Override
    public CustomKeyboardDto buildCustomKeyboard(CustomKeyboardRequest customKeyboardRequest) {
        CustomKeyboardDto customKeyboardDto = new CustomKeyboardDto();

        KitBatchDto kitBatchDto = kitBatchService.checkStockAvailability(customKeyboardDto.getKitModel(), customKeyboardRequest.getKitColor());
        KeyCapSetDto keyCapSetDto = keycapSetService.getAllInStock(customKeyboardDto.getKeyCapSet());
        SwitchBatchDto switchBatchDto = switchBatchService.checkStockAvailability(customKeyboardDto.getSwitchName());
        if (switchBatchDto != null)
            customKeyboardDto.setSwitchName(switchBatchDto.getSwitchName());
        else
            customKeyboardDto.setSwitchName("Out of Stock for this switch");
        if (keyCapSetDto != null)
            customKeyboardDto.setKeyCapSet(keyCapSetDto.getName());
        else
            customKeyboardDto.setKeyCapSet("Out of Stock for this Keycap");
        if (kitBatchDto != null)
            customKeyboardDto.setKitModel(kitBatchDto.getModel());
        else
            customKeyboardDto.setKeyCapSet("Out of Stock for this Kit");
        customKeyboardDto.setColor(kitBatchDto.getColor());

        double totalPrice = (switchBatchDto.getPricePerUnit() * kitBatchDto.getLayOut()
                + kitBatchDto.getPricePerUnit() + keyCapSetDto.getPrice()) * CustomKeyboard.assembleRate;
        customKeyboardDto.setAssemblePrice(totalPrice);
        return customKeyboardDto;
    }
}
