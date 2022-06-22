package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitDto {
    private Integer layOut;

    private String model;

    private int quantity;

    private int soldUnits;

    private double pricePerUnit;

    private Status status;

    private String manufacturerName;

    private String color;

    private float  weight;

    private Material material;

    private PcbDto pcbDto;

    private PLateDto pLateDto;

    private StabilizerDto stabilizerDto;


}
