package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PcbRepoTest {
@Autowired
private PcbRepo pcbRepo;
    @Test
    void findByKitBatchModel() {
        Assertions.assertEquals(null,pcbRepo.findByKitBatchModel("docke"));
    }
}