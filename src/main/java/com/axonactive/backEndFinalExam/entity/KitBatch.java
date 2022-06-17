package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitBatch  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer layOut;

    private LocalDate manufacturedDate;

    private String model;

    private int quantity;

    private int soldUnits;

    private LocalDate importedDate;

    private LocalDate soldOutDate;

    private double pricePerUnit;

    private String color;

    private float  weight;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Material material;

    @ManyToOne
    @JoinColumn
    private Manufacturer manufacturer;

}
