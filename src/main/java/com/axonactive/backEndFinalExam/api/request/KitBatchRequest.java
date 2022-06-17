package com.axonactive.backEndFinalExam.api.request;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitBatchRequest {
    private String kitBatchName;

    private int quantity;

    private Integer layOut;

    private LocalDate manufacturedDate;

    private String model;

    private LocalDate importedDate;


    private double pricePerUnit;

    private String color;

    private float  weight;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String manufacturerName;
}
