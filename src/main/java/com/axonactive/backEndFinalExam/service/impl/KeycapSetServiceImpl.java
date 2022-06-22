package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KeyCapSetRequest;
import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.entity.enumClazz.ItemType;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.KeyCapSetRepository;
import com.axonactive.backEndFinalExam.repository.PriceUpdateRecordRepo;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeycapSetServiceImpl implements KeycapSetService {

    @Autowired
    private KeyCapSetRepository keyCapSetRepo;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private PriceUpdateRecordRepo priceUpdateRecordRepo;

    @Override
    public List<KeyCapSet> getAll() {
        return keyCapSetRepo.findAll();
    }

    @Override
    public Optional<KeyCapSet> findById(Integer id) {
        return keyCapSetRepo.findById(id);
    }

    @Override
    public KeyCapSet save(KeyCapSet keyCapSet) {
        keyCapSet.setSoldUnits(0);
        return keyCapSetRepo.save(keyCapSet);
    }

    @Override
    public void deleteById(Integer id) {
        keyCapSetRepo.deleteById(id);
    }

    @Override
    public KeyCapSetDto getAllInStock(String name) {
        KeyCapSetDto keyCapSearchDto = new KeyCapSetDto();

        List<KeyCapSet> keyCapList = keyCapSetRepo.getAllInStock(name).stream()
                .sorted(Comparator.comparing(KeyCapSet::getImportedDate).reversed())
                .collect(Collectors.toList());

        getQuantityOfTheInstockKeycaps(keyCapSearchDto, keyCapList);

        getSoldUnits(name, keyCapSearchDto);

        if (keyCapList.get(0).getManufacturer() == null)
            keyCapSearchDto.setManufacturerName("Not Found Manufacturer");
        else
            keyCapSearchDto.setManufacturerName(keyCapList.get(0).getManufacturer().getName());
        setKeyCapDto(keyCapSearchDto, keyCapList);

        return keyCapSearchDto;
    }

    private void setKeyCapDto(KeyCapSetDto keyCapSearchDto, List<KeyCapSet> keyCapList) {
        keyCapSearchDto.setColor(keyCapList.get(0).getColor());
        keyCapSearchDto.setKeycapProfile(keyCapList.get(0).getKeycapProfile());
        keyCapSearchDto.setKeyCabPrintingTechnique(keyCapList.get(0).getKeyCabPrintingTechnique());
        keyCapSearchDto.setName(keyCapList.get(0).getName());
        keyCapSearchDto.setMaterial(keyCapList.get(0).getMaterial());
        keyCapSearchDto.setPrice(keyCapList.get(0).getPrice());
        keyCapSearchDto.setStatus(Status.INSTOCK);
    }

    private void getSoldUnits(String name, KeyCapSetDto keyCapSearchDto) {
        keyCapSearchDto.setSoldUnits(0);
        for (KeyCapSet batch : keyCapSetRepo.getSoldUnits(name)
        ) {
            keyCapSearchDto.setSoldUnits(keyCapSearchDto.getSoldUnits() + batch.getSoldUnits());
        }
    }

    private void getQuantityOfTheInstockKeycaps(KeyCapSetDto keyCapSearchDto, List<KeyCapSet> keyCapList) {
        int quantity = 0;
        keyCapSearchDto.setQuantity(0);
        for (KeyCapSet batch : keyCapList
        ) {
            quantity += (batch.getQuantity() - batch.getSoldUnits() + keyCapSearchDto.getQuantity());
        }
        keyCapSearchDto.setQuantity(quantity);
    }

    @Override
    public List<KeyCapSet> getAllKeyCapSetWithNameAndImportedDate(String name, LocalDate date) {
        return keyCapSetRepo.getAllKeyCapSetFromADayWithGivenName(name, date);
    }

    @Override
    public List<KeyCapSet> getAllKeyCapSetWithImportedDate(LocalDate date) {
        return keyCapSetRepo.getAllKeyCapSetFromADay(date);
    }

    @Override
    public KeyCapSet saveARequest(KeyCapSetRequest keyCapSetRequest) throws ResourceNotFoundException {
        List<KeyCapSet> keycapSetList = keyCapSetRepo.getSoldUnits(keyCapSetRequest.getName())
                .stream()
                .filter(k -> k.getStatus() == Status.INSTOCK)
                .collect(Collectors.toList());


        if (!keycapSetList.isEmpty()) {

            for (KeyCapSet keyCapSet : keycapSetList
            ) {
                int total = 0;
                PriceUpdateRecord keyCapSetPriceUpdate = new PriceUpdateRecord();
                keyCapSetPriceUpdate.setUpdatePrice(keyCapSetRequest.getPrice());
                keyCapSetPriceUpdate.setUpdatePriceDate(LocalDate.now());
                total = (keyCapSet.getQuantity() - keyCapSet.getSoldUnits());
                keyCapSetPriceUpdate.setItemType(ItemType.KeyCapSet);
                keyCapSetPriceUpdate.setUpdateQuantity(total);
                keyCapSetPriceUpdate.setItemId(keyCapSet.getId());
                priceUpdateRecordRepo.save(keyCapSetPriceUpdate);
            }


        } else
            throw new ResourceNotFoundException("This keyCapSet does not exist in memory");

        return saveRequest(keyCapSetRequest, keycapSetList.get(0));
    }

    @Override
    public List<KeyCapSet> getAllKeyCapSetFromAManufacturer(String name) {
        return keyCapSetRepo.getAllKeyCapSetFromAManufacturer(name);
    }

    private KeyCapSet saveRequest(KeyCapSetRequest keycapRequest, KeyCapSet keycapSet) {
        KeyCapSet keycapSetNew = new KeyCapSet();
        keycapSetNew.setName(keycapSet.getName());
        keycapSetNew.setKeycapProfile(keycapSet.getKeycapProfile());
        keycapSetNew.setImportedDate(LocalDate.now());
        keycapSetNew.setStatus(keycapRequest.getStatus());
        keycapSetNew.setQuantity(keycapRequest.getQuantity());
        keycapSetNew.setColor(keycapSet.getColor());
        keycapSetNew.setManufacturer(keycapSet.getManufacturer());
        keycapSetNew.setKeyCabPrintingTechnique(keycapSet.getKeyCabPrintingTechnique());
        keycapSetNew.setPrice(keycapRequest.getPrice());
        keycapSetNew.setSoldUnits(0);
        keycapSetNew.setMaterial(keycapSet.getMaterial());

        return keyCapSetRepo.save(keycapSetNew);

    }

    @Override
    public List<KeyCapSetDto> almostOutOfStockKeyCapSet() throws ResourceNotFoundException {
        List<KeyCapSetDto> almostOutOfStock = new ArrayList<>();


        for (String name : listOfAllKeyCapSetName()
        ) {
            KeyCapSetDto newKeyCap = getAllInStock(name);
            if (newKeyCap.getQuantity() < 20)
                almostOutOfStock.add(newKeyCap);
        }
        return almostOutOfStock;
    }

    public Set<String> listOfAllKeyCapSetName() throws ResourceNotFoundException {
        Set<String> newSet = new HashSet<>();
        List<KeyCapSet> keyCapSetList = getAll();
        for (KeyCapSet kit : keyCapSetList
        ) {
            if (!keyCapSetList.isEmpty())
                newSet.add(kit.getName());

        }
        return newSet;
    }

    @Override
    public List<KeyCapSet> keyCapSetBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException {
        List<KeyCapSet> keyCapSetList = getAll();
        if (keyCapSetList.isEmpty())
            throw new ResourceNotFoundException("No keycap set found in database");
        return keyCapSetList
                .stream()
                .filter(k -> k.getImportedDate().getMonth() == (Month.of(month)))
                .filter(k -> k.getImportedDate().getYear() == year)
                .collect(Collectors.toList());
    }

}

