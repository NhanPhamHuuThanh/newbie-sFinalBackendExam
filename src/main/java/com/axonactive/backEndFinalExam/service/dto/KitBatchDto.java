package com.axonactive.backEndFinalExam.service.dto;


import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
import com.axonactive.backEndFinalExam.entity.enumClazz.pcbType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitBatchDto {
    private String layOut;

    private String size;

    private LocalDate manufacturedDate;

    private String model;

    private double pricePerUnit;

    private MountType plateMountType;

    private String caseColor;

    private Material material;

    private double caseWeight;

    private pcbType pcbType;

    private String manufacturerName;
}
