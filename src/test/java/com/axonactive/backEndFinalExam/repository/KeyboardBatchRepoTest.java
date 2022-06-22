package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class KeyboardBatchRepoTest {

    @Autowired
    private KeyboardBatchRepo keyboardBatchRepo;

    @Test
    void shouldReturn0WhenFirstTested(){
    Assertions.assertEquals(0,keyboardBatchRepo.getAllInStock("001").size());
    }

    @Test
    void getAllKeyboardBatchFromAManufacturer() {
        Assertions.assertEquals(0,keyboardBatchRepo.getAllKeyboardBatchFromAManufacturer("JOe").size());
    }
}