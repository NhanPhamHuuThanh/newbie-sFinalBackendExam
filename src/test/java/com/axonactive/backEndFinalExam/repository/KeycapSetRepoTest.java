package com.axonactive.backEndFinalExam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class KeycapSetRepoTest {
    @Autowired
    private KeycapSetRepo keycapSetRepo;

    @Test
    void shouldReturn0WhenFirstCreated() {
        Assertions.assertEquals(0, keycapSetRepo.getAllInStock("001").size());
    }

    @Test
    void shouldReturn0WhenNotFound() {
        Assertions.assertEquals(0,keycapSetRepo.getAllKeyCapSetFromADayWithGivenName("001", LocalDate.parse("2002-02-02")).size());
    }
}