package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HousingRepoTest {
    @Autowired
    private HousingRepo housingRepo;

    @Test
    void shouldReturnNullWhenNoObjectIsFound() {
        Assertions.assertEquals(null, housingRepo.findBySwitchBatchSwitchName("anubis"));
    }
}