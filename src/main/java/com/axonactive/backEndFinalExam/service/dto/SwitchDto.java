package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchDto {

    private SwitchType switchType;

    private int quantity;

    private int soldUnits;

    private double pricePerUnit;

    private String switchName;

    private Status status;

    private String manufacturerName;

    private HousingDto housing;

    private SwitchStringDto switchSpring;

    private StemDto stem;

}
