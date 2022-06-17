package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.Customer;
import com.axonactive.backEndFinalExam.entity.enumClazz.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomKeyboardDto {
    private String switchName;

    private String kitModel;

    private String keyCapSet;

    private String color;

    private double assemblePrice;

    private LocalDate expectedDeliveryDate;

    private LocalDate actualDeliveryDate;

    private int insuranceEnd;

    @Enumerated(EnumType.STRING)
    private Payment payment;


    private Customer customer;
}
