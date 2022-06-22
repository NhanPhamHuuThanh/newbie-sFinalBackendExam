package com.axonactive.backEndFinalExam.service.impl;

import com.axonactive.backEndFinalExam.service.KeycapSetService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class KeycapSetServiceImplTest {
@Autowired
 private KeycapSetService keycapSetService;
    @Test
    void getAllKeyCapSetFromAManufacturer() {
        Assertions.assertEquals(0,keycapSetService.getAllKeyCapSetFromAManufacturer("GMMK").size());
    }
}