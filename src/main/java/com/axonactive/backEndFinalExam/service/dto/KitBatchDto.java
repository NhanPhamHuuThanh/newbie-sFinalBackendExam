package com.axonactive.backEndFinalExam.service.dto;


import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitBatchDto {

    private Integer layOut;

    private LocalDate manufacturedDate;

    private String model;

    private int quantity;

    private int soldUnits;

    private LocalDate importedDate;

    private double pricePerUnit;

    private Status status;

    private String manufacturerName;

    private String color;

    private float  weight;

    private Material material;



}
