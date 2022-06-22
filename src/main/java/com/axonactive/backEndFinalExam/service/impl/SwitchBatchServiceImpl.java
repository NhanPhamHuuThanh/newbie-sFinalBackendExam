package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.SwitchBatchRequest;
import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.entity.SwitchBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.ItemType;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.*;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.SwitchBatchService;
import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import com.axonactive.backEndFinalExam.service.dto.SwitchDto;
import com.axonactive.backEndFinalExam.service.mapper.HousingMapper;
import com.axonactive.backEndFinalExam.service.mapper.StemMapper;
import com.axonactive.backEndFinalExam.service.mapper.SwitchStringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SwitchBatchServiceImpl implements SwitchBatchService {
    @Autowired
    private SwitchBatchRepo switchBatchRepo;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private PriceUpdateRecordRepo priceUpdateRecordRepo;
    @Autowired
    private HousingRepo housingRepo;
    @Autowired
    private SwitchSpringRepo switchSpringRepo;
    @Autowired
    private StemRepo stemRepo;


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
        List<SwitchBatch> switchBatchList = switchBatchRepo.getAllInstock(name)
                .stream()
                .sorted(Comparator.comparing(SwitchBatch::getImportedDate).reversed())
                .collect(Collectors.toList());

        getQuantityForInstockSwitchBatch(switchSearchedDto, switchBatchList);
        getSoldUnits(name, switchSearchedDto);

        if (switchBatchList.get(0).getManufacturer()==null)
            switchSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            switchSearchedDto.setManufacturerName(switchBatchList.get(0).getManufacturer().getName());
        setSwitchSearchInfo(switchSearchedDto, switchBatchList);

        return switchSearchedDto;
    }

    private void setSwitchSearchInfo(SwitchBatchDto switchSearchedDto, List<SwitchBatch> switchBatchList) {
        switchSearchedDto.setPricePerUnit(switchBatchList.get(0).getPricePerUnit());
        switchSearchedDto.setSwitchName(switchBatchList.get(0).getSwitchName());
        switchSearchedDto.setSwitchType(switchBatchList.get(0).getSwitchType());
        switchSearchedDto.setStatus(Status.INSTOCK);
    }

    private void getSoldUnits(String name, SwitchBatchDto switchSearchedDto) {
        switchSearchedDto.setSoldUnits(0);

        for (SwitchBatch batch : switchBatchRepo.getSoldUnits(name)
        ) {
            switchSearchedDto.setSoldUnits(switchSearchedDto.getSoldUnits() + batch.getSoldUnits());
        }
    }

    private void getQuantityForInstockSwitchBatch(SwitchBatchDto switchSearchedDto, List<SwitchBatch> switchBatchList) {
        switchSearchedDto.setQuantity(0);
        for (SwitchBatch batch : switchBatchList
        ) {
            switchSearchedDto.setQuantity(switchSearchedDto.getQuantity() + batch.getQuantity() - batch.getSoldUnits());
        }
    }

    @Override
    public SwitchBatch saveWithExistData(SwitchBatchRequest switchBatchRequest) throws ResourceNotFoundException {
        List<SwitchBatch> switchBatchList = switchBatchRepo.getAllInstock(switchBatchRequest.getSwitchName())
                .stream()
                .filter(k -> k.getStatus() == Status.INSTOCK)
                .collect(Collectors.toList());

        if (!switchBatchList.isEmpty()) {

            for (SwitchBatch switchBatch : switchBatchList
            ) {
                int total = 0;
                PriceUpdateRecord switchPriceUpdate = new PriceUpdateRecord();
                switchPriceUpdate.setUpdatePrice(switchBatchRequest.getPricePerUnit());
                switchPriceUpdate.setUpdatePriceDate(LocalDate.now());
                total = (switchBatch.getQuantity() - switchBatch.getSoldUnits());
                switchPriceUpdate.setUpdateQuantity(total);
                switchPriceUpdate.setItemType(ItemType.Switchbatch);
                priceUpdateRecordRepo.save(switchPriceUpdate);

            }

        } else
            throw new ResourceNotFoundException("This switchBatch does not exist in memory");

        return saveRequest(switchBatchRequest, switchBatchList.get(0));
    }

    @Override
    public List<SwitchBatch> listOfAllSwitchBatchOnAGivenDay(LocalDate date) {
        return switchBatchRepo.getAllSwitchBatchFromADay(date);
    }

    public SwitchBatch saveRequest(SwitchBatchRequest switchBatchRequest, SwitchBatch switchBatch) {
        SwitchBatch switchBatchNew = SwitchBatch.builder()
                .switchName(switchBatch.getSwitchName())
                .switchType(switchBatch.getSwitchType())
                .importedDate(switchBatchRequest.getImportedDate())
                .quantity(switchBatch.getQuantity())
                .soldUnits(0)
                .pricePerUnit(switchBatch.getPricePerUnit())
                .manufacturer(switchBatch.getManufacturer())
                .status(switchBatchRequest.getStatus())
                .build();
        return switchBatchRepo.save(switchBatchNew);

    }



    @Override
    public List<SwitchBatch> switchBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException {
        List<SwitchBatch> switchBatchList = getAll();

        if (switchBatchList.size() <= 0)
            throw new ResourceNotFoundException("No switch batch found in database");
        return switchBatchList
                .stream()
                .filter(k -> k.getImportedDate().getMonth() == (Month.of(month)))
                .filter(k -> k.getImportedDate().getYear() == year)
                .collect(Collectors.toList());

    }

    @Override
    public List<SwitchBatchDto> almostOutOfStockSwitchBatch() throws ResourceNotFoundException {
        List<SwitchBatchDto> almostOutOfStock = new ArrayList<>();

        for (String name : listOfAllSwitchBatchName()
        ) {
            if (checkStockAvailability(name).getQuantity() < 20)
                almostOutOfStock.add(checkStockAvailability(name));
        }
        return almostOutOfStock;
    }

    @Override
    public SwitchDto findSwitchForCustomer(String name) {
        SwitchDto switchDto = new SwitchDto();
        SwitchBatchDto switchBatchDto = checkStockAvailability(name);

        switchDto.setSwitchType(switchBatchDto.getSwitchType());
        switchDto.setQuantity(switchBatchDto.getQuantity());
        switchDto.setSoldUnits(switchBatchDto.getSoldUnits());
        switchDto.setPricePerUnit(switchBatchDto.getPricePerUnit());
        switchDto.setSwitchName(switchBatchDto.getSwitchName());
        switchDto.setStatus(switchBatchDto.getStatus());
        switchDto.setManufacturerName(switchBatchDto.getManufacturerName());
        switchDto.setHousing(HousingMapper.INSTANCE.toDto(housingRepo.findBySwitchBatchSwitchName(name)));
        switchDto.setSwitchSpring(SwitchStringMapper.INSTANCE.toDto(switchSpringRepo.findBySwitchBatchSwitchName(name)));
        switchDto.setStem(StemMapper.INSTANCE.toDto(stemRepo.findBySwitchBatchSwitchName(name)));

        return switchDto;
    }


    public Set<String> listOfAllSwitchBatchName() throws ResourceNotFoundException {
        Set<String> newSet = new HashSet<>();
        List<SwitchBatch> switchBatchList = getAll();

        for (SwitchBatch switchBatch : switchBatchList
        ) {
            if (!switchBatchList.isEmpty())
                newSet.add(switchBatch.getSwitchName());
        }
        return newSet;
    }
}





