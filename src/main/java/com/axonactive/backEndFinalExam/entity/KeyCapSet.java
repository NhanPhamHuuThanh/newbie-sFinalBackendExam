package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapPrintingTechnique;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapProfile;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
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
    @GeneratedValue
    private Integer id;

    @Enumerated
    private Material material;

    private String color;

    @Enumerated
    private KeycapPrintingTechnique keyCabPrintingTechnique;

    @Enumerated
    private KeycapProfile keycapProfile;

    @JoinColumn
    @ManyToOne
    private Manufacturer manufacturer;

    private LocalDate importedDate;

    private double price;
}
