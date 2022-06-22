package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SwitchBatchRepoTest {

    @Autowired
    private SwitchBatchRepo switchBatchRepo;

@Test
    void shouldReturn0WhenFirstTested(){
    Assertions.assertEquals(0,switchBatchRepo.getAllInstock("001").size());
}
@Test
    void shouldReturn0WhereThereArentAnyManufacturer(){
    Assertions.assertEquals(0,switchBatchRepo.getAllSwitchBatchFromAManufacturer("Tescee").size());
}
}