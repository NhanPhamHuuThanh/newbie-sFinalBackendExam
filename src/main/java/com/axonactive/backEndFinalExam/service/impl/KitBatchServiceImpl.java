package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;
import com.axonactive.backEndFinalExam.api.request.KitBatchRequest;
import com.axonactive.backEndFinalExam.entity.KitBatch;
import com.axonactive.backEndFinalExam.entity.PriceUpdateRecord;
import com.axonactive.backEndFinalExam.entity.Stabilizer;
import com.axonactive.backEndFinalExam.entity.enumClazz.ItemType;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.repository.*;
import com.axonactive.backEndFinalExam.service.KitBatchService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;
import com.axonactive.backEndFinalExam.service.dto.KitDto;
import com.axonactive.backEndFinalExam.service.dto.PLateDto;
import com.axonactive.backEndFinalExam.service.mapper.PcbMapper;
import com.axonactive.backEndFinalExam.service.mapper.PlateMapper;
import com.axonactive.backEndFinalExam.service.mapper.StabilizerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KitBatchServiceImpl implements KitBatchService {

    @Autowired
    private KitBatchRepo kitBatchRepo;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private PriceUpdateRecordRepo priceUpdateRecordRepo;
    @Autowired
    private StabilizerRepo stabilizerRepo;
    @Autowired
    private PlateRepo plateRepo;
    @Autowired
    private PcbRepo pcbRepo;


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
        kitBatch.setQuantity(0);
        return kitBatchRepo.save(kitBatch);
    }

    @Override
    public void deleteById(Integer id) {
        kitBatchRepo.deleteById(id);
    }

    @Override
    public KitBatchDto checkStockAvailability(String name, String color) {
        KitBatchDto kitSearchedDto = new KitBatchDto();

        List<KitBatch> kitBatchList = kitBatchRepo.getAllInStock(name, color)
                .stream()
                .sorted(Comparator.comparing(KitBatch::getImportedDate).reversed())
                .collect(Collectors.toList());

        getQuantityOfInStockKit(kitSearchedDto, kitBatchList);
        getSoldUnitsOfKit(name, color, kitSearchedDto);

        if (kitBatchList.get(0).getManufacturer() == null)
            kitSearchedDto.setManufacturerName("Not Found Manufacturer");
        else
            kitSearchedDto.setManufacturerName(kitBatchList.get(0).getManufacturer().getName());
        setKitDto(name, color, kitSearchedDto, kitBatchList);

        return kitSearchedDto;
    }

    private void setKitDto(String name, String color, KitBatchDto kitSearchedDto, List<KitBatch> kitBatchList) {
        kitSearchedDto.setColor(color);
        kitSearchedDto.setLayOut(kitBatchList.get(0).getLayOut());
        kitSearchedDto.setPricePerUnit(kitBatchList.get(0).getPricePerUnit());
        kitSearchedDto.setWeight(kitBatchList.get(0).getWeight());
        kitSearchedDto.setModel(name);
        kitSearchedDto.setColor(color);
        kitSearchedDto.setStatus(Status.INSTOCK);
    }

    private void getSoldUnitsOfKit(String name, String color, KitBatchDto kitSearchedDto) {
        kitSearchedDto.setSoldUnits(0);
        for (KitBatch batch : kitBatchRepo.getSoldUnits(name, color)
        ) {
            kitSearchedDto.setSoldUnits(kitSearchedDto.getSoldUnits() + batch.getSoldUnits());
        }
    }

    private void getQuantityOfInStockKit(KitBatchDto kitSearchedDto, List<KitBatch> kitBatchList) {
        kitSearchedDto.setQuantity(0);
        for (KitBatch batch : kitBatchList
        ) {
            kitSearchedDto.setQuantity(kitSearchedDto.getQuantity() + batch.getQuantity() - batch.getSoldUnits());
        }

    }

    @Override
    public KitBatch createPriceUpdateRecord(KitBatchRequest kitBatchRequest) throws ResourceNotFoundException {
        List<KitBatch> kitBatchList = kitBatchRepo.getAll(kitBatchRequest.getKitBatchName(), kitBatchRequest.getColor())
                .stream()
                .filter(k -> k.getStatus() == Status.INSTOCK)
                .collect(Collectors.toList());


        if (!kitBatchList.isEmpty()) {

            for (KitBatch kitBatch : kitBatchList
            ) {
                int total = 0;
                PriceUpdateRecord kitBatchPriceUpdate = new PriceUpdateRecord();
                kitBatchPriceUpdate.setUpdatePrice(kitBatchRequest.getPricePerUnit());
                kitBatchPriceUpdate.setUpdatePriceDate(LocalDate.now());
                total = (kitBatch.getQuantity() - kitBatch.getSoldUnits());
                kitBatchPriceUpdate.setUpdateQuantity(total);
                kitBatchPriceUpdate.setItemType(ItemType.KitBatch);
                priceUpdateRecordRepo.save(kitBatchPriceUpdate);
            }


        } else
            throw new ResourceNotFoundException("This kitBatch does not exist in database");

        return saveRequest(kitBatchRequest, kitBatchList.get(0));
    }

    @Override
    public List<KitBatch> listOfAllKitBatchOnAGivenDay(LocalDate date) {
        return kitBatchRepo.getAllKitBatchFromADay(date);
    }

    public KitBatch saveRequest(KitBatchRequest kitBatchRequest, KitBatch kitBatch) {
        KitBatch kitBatchNew = KitBatch.builder()
                .model(kitBatch.getModel())
                .color(kitBatchRequest.getColor())
                .importedDate(kitBatchRequest.getImportedDate())
                .layOut(kitBatch.getLayOut())
                .material(kitBatch.getMaterial())
                .soldUnits(0)
                .manufacturer(kitBatch.getManufacturer())
                .pricePerUnit(kitBatchRequest.getPricePerUnit())
                .weight(kitBatch.getWeight())
                .status(kitBatchRequest.getStatus())
                .build();
        return kitBatchRepo.save(kitBatchNew);

    }

    @Override
    public List<KitBatchDto> almostOutOfStockKitBatch() throws ResourceNotFoundException {
        List<KitBatchDto> almostOutOfStock = new ArrayList<>();
        for (String name : listOfAllKitBatchName()
        ) {
            for (String color : listOfAllKitBatchColor(name)
            ) {
                if (checkStockAvailability(name, color).getQuantity() < 20)
                    almostOutOfStock.add(checkStockAvailability(name, color));
            }
        }
        return almostOutOfStock;
    }


    public Set<String> listOfAllKitBatchColor(String name) throws ResourceNotFoundException {
        Set<String> newSet = new HashSet<>();
        for (KitBatch kit : kitBatchRepo.findAll()
        ) {
            if (kit.getModel().equalsIgnoreCase(name))
                newSet.add(kit.getColor());
        }
        return newSet;
    }

    public Set<String> listOfAllKitBatchName() throws ResourceNotFoundException {
        Set<String> newSet = new HashSet<>();
        List<KitBatch> kitBatchList = kitBatchRepo.findAll();
        for (KitBatch kit : kitBatchList
        ) {
            if (!kitBatchList.isEmpty())
                newSet.add(kit.getModel());
            else
                throw new ResourceNotFoundException("No kit batch found in data base");
        }
        return newSet;
}

    @Override
    public List<KitBatch> kitBatchImportedDateWasInMonth(int month, int year) throws ResourceNotFoundException {
        List<KitBatch> kitBatchList = findAll();
        if (kitBatchList.isEmpty())
            throw new ResourceNotFoundException("No Kit batch found in database");
        return kitBatchList
                .stream()
                .filter(k -> k.getImportedDate().getMonth() == (Month.of(month)))
                .filter(k -> k.getImportedDate().getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public KitDto findKitForCustomer(String name,String color) {
        KitDto kitDto = new KitDto();
        KitBatchDto kitBatchDto = checkStockAvailability(name,color);

        kitDto.setColor(kitBatchDto.getColor());
        kitDto.setModel(kitBatchDto.getModel());
        kitDto.setLayOut(kitBatchDto.getLayOut());
        kitDto.setManufacturerName(kitBatchDto.getManufacturerName());
        kitDto.setQuantity(kitBatchDto.getQuantity());
        kitDto.setSoldUnits(kitBatchDto.getSoldUnits());
        kitDto.setStatus(kitBatchDto.getStatus());
        kitDto.setWeight(kitBatchDto.getWeight());
        kitDto.setPricePerUnit(kitBatchDto.getPricePerUnit());
        kitDto.setMaterial(kitBatchDto.getMaterial());

        kitDto.setPLateDto(PlateMapper.INSTANCE.toDto(plateRepo.findByKitBatchModel(name)));
        kitDto.setPcbDto(PcbMapper.INSTANCE.toDto(pcbRepo.findByKitBatchModel(name)));
        kitDto.setStabilizerDto(StabilizerMapper.INSTANCE.toDto(stabilizerRepo.findStabilizerFromKitBatch(name)));

        return kitDto;
    }


}
