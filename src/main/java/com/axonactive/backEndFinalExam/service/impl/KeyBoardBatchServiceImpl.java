package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyboardRequest;
import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.entity.enumClazz.ItemType;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.KeyboardBatchRepo;
import com.axonactive.backEndFinalExam.repository.PriceUpdateRecordRepo;
import com.axonactive.backEndFinalExam.service.KeyBoardBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeyBoardBatchServiceImpl implements KeyBoardBatchService {
    @Autowired
    private KeyboardBatchRepo keyboardBatchRepo;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private PriceUpdateRecordRepo priceUpdateRecordRepo;


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
        keyboardBatch.setSoldUnits(0);
        return keyboardBatchRepo.save(keyboardBatch);
    }

    @Override
    public void deleteById(Integer id) {
        keyboardBatchRepo.deleteById(id);
    }

    @Override
    public KeyboardBatchDto checkStockAvailability(String name) {
        KeyboardBatchDto keyboardSearchedDto = new KeyboardBatchDto();

        List<KeyboardBatch> keyboardList = keyboardBatchRepo.getAllInStock(name)
                .stream()
                .sorted(Comparator.comparing(KeyboardBatch::getImportedDate).reversed())
                .collect(Collectors.toList());

        getAllQuantityInstock(keyboardSearchedDto, keyboardList);
        getSoldUnits(name, keyboardSearchedDto);

        if (keyboardList.get(0).getManufacturer() == null)
            keyboardSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            keyboardSearchedDto.setManufacturerName(keyboardList.get(0).getManufacturer().getName());
        setKeyboardDto(keyboardSearchedDto, keyboardList);

        return keyboardSearchedDto;
    }

    private void setKeyboardDto(KeyboardBatchDto keyboardSearchedDto, List<KeyboardBatch> keyboardList) {
        keyboardSearchedDto.setPricePerUnit(keyboardList.get(0).getPricePerUnit());
        keyboardSearchedDto.setKeyCapName(keyboardList.get(0).getKeyCapName());
        keyboardSearchedDto.setSwitchName(keyboardList.get(0).getSwitchName());
        keyboardSearchedDto.setKitName(keyboardList.get(0).getKitName());
        keyboardSearchedDto.setManufacturerName(keyboardList.get(0).getManufacturer().getName());
        keyboardSearchedDto.setInsuranceWarrantyMonth(keyboardList.get(0).getInsuranceWarrantyMonth());
        keyboardSearchedDto.setStatus(Status.INSTOCK);

    }

    private void getSoldUnits(String name, KeyboardBatchDto keyboardSearchedDto) {
        keyboardSearchedDto.setSoldUnits(0);
        for (KeyboardBatch batch : keyboardBatchRepo.getSoldUnits(name)
        ) {
            keyboardSearchedDto.setSoldUnits(keyboardSearchedDto.getSoldUnits() + batch.getSoldUnits());
        }
    }

    private void getAllQuantityInstock(KeyboardBatchDto keyboardSearchedDto, List<KeyboardBatch> keyboardList) {
        keyboardSearchedDto.setQuantity(0);
        for (KeyboardBatch batch : keyboardList
        ) {
            keyboardSearchedDto.setQuantity(keyboardSearchedDto.getQuantity() + batch.getQuantity() - batch.getSoldUnits());
        }
    }

    @Override
    public KeyboardBatch saveWithExistData(KeyboardRequest keyboardRequest) throws ResourceNotFoundException {

        List<KeyboardBatch> keyboardBatchList = keyboardBatchRepo.getSoldUnits(keyboardRequest.getName())
                .stream()
                .filter(k -> k.getStatus() == Status.INSTOCK)
                .collect(Collectors.toList());


        if (!keyboardBatchList.isEmpty()) {

            for (KeyboardBatch keyboard : keyboardBatchList
            ) {
                int total = 0;
                PriceUpdateRecord keyboardPriceUpdate = new PriceUpdateRecord();
                keyboardPriceUpdate.setUpdatePrice(keyboardRequest.getPricePerUnit());
                keyboardPriceUpdate.setUpdatePriceDate(LocalDate.now());
                total = (keyboard.getQuantity() - keyboard.getSoldUnits());
                keyboardPriceUpdate.setItemType(ItemType.KeyboardBatch);
                keyboardPriceUpdate.setUpdateQuantity(total);
                keyboardPriceUpdate.setItemId(keyboard.getId());
                priceUpdateRecordRepo.save(keyboardPriceUpdate);
            }

        } else
            throw new ResourceNotFoundException("This keyboard does not exist in memory");


        return saveRequest(keyboardRequest, keyboardBatchList.get(0));
    }

    @Override
    public List<KeyboardBatch> listOfKeyboardBatchInAGivenDay(LocalDate date) {
        return keyboardBatchRepo.getAllKeyboardBatchFromADay(date);
    }

    private KeyboardBatch saveRequest(KeyboardRequest keyboardRequest, KeyboardBatch keyboardBatch) {
        KeyboardBatch keyboardBatchNew = KeyboardBatch.builder()
                .kitName(keyboardBatch.getKitName())
                .switchName(keyboardBatch.getSwitchName())
                .importedDate(keyboardRequest.getImportedDate())
                .status(keyboardRequest.getStatus())
                .keyCapName(keyboardBatch.getKeyCapName())
                .insuranceWarrantyMonth(keyboardRequest.getInsuranceWarrantyMonth())
                .quantity(keyboardRequest.getQuantity())
                .manufacturer(keyboardBatch.getManufacturer())
                .name(keyboardRequest.getName())
                .soldUnits(0)
                .build();
        return keyboardBatchRepo.save(keyboardBatchNew);


    }

    @Override
    public List<KeyboardBatchDto> almostOutOfStockKeyBoardBatch() throws ResourceNotFoundException {
        List<KeyboardBatchDto> almostOutOfStock = new ArrayList<>();

        for (String name : listOfAllKeyBoardBatchName()
        ) {
            if (checkStockAvailability(name).getQuantity() < 20)
                almostOutOfStock.add(checkStockAvailability(name));
        }
        return almostOutOfStock;
    }

    @Override
    public List<KeyboardBatch> keyBoardBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException {
        List<KeyboardBatch> keyboardBatchList = getAll();

        if (keyboardBatchList.isEmpty())
            throw new ResourceNotFoundException("No keyboard batch found in database");
        return keyboardBatchList.stream()
                .filter(k -> k.getImportedDate().getMonth() == (Month.of(month)))
                .filter(k -> k.getImportedDate().getYear() == year)
                .collect(Collectors.toList());
    }



    public Set<String> listOfAllKeyBoardBatchName() throws ResourceNotFoundException {
        Set<String> newSet = new HashSet<>();
        List<KeyboardBatch> keyboardBatchList = getAll();

        for (KeyboardBatch kit : keyboardBatchList
        ) {
            if (!keyboardBatchList.isEmpty())
                newSet.add(kit.getName());
        }
        return newSet;
    }

}

