package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class KeyCapPriceUpdateRepoTest {
    @Autowired
    private PriceUpdateRecordRepo keyCapPriceUpdateRepo;

    @Test
    void shouldReturn0WhenThereArentAnyPriceUpdateInABatch() {
        Assertions.assertEquals(0, keyCapPriceUpdateRepo.findAllTheUpdateHistoryOfKeyCapSet("001", LocalDate.parse("2002-02-02")));
    }
}
