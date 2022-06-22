package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlateRepoTest {
    @Autowired
    private PlateRepo plateRepo;

    @Test
    void findByKitBatchModel() {
        Assertions.assertEquals(null,plateRepo.findByKitBatchModel("log"));
    }
}