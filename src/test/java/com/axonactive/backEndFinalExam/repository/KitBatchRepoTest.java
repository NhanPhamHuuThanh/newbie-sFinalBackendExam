package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class KitBatchRepoTest {
    @Autowired
    private KitBatchRepo kitBatchRepo;

    @Test
    void shouldReturn0WhenFirstTested(){
        Assertions.assertEquals(0,kitBatchRepo.getAllInStock("001","002").size());
}

}