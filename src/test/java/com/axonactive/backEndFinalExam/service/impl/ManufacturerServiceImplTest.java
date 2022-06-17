package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.service.KeycapSetService;
import com.axonactive.backEndFinalExam.service.ManufacturerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ManufacturerServiceImplTest {
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    KeycapSetService keycapSetService;
    @BeforeEach
    void inputData(){
        KeyCapSet keyCapSet1 = KeyCapSet.builder()
                .color("yellow")
                .price(100)
                .build();
        keycapSetService.save(keyCapSet1);
        Manufacturer manufacturer1 =Manufacturer.builder()
                .build();
        manufacturerService.save(manufacturer1);
    }
}