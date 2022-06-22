package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapPrintingTechnique;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapProfile;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyCapSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Material material;

    private String color;

    @Enumerated(EnumType.STRING)
    private KeycapPrintingTechnique keyCabPrintingTechnique;

    @Enumerated(EnumType.STRING)
    private KeycapProfile keycapProfile;

    private LocalDate importedDate;

    @Column
    private double price;

    private LocalDate soldOutDate;

    private int quantity;

    private int soldUnits;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn
    private Manufacturer manufacturer;
}
