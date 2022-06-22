package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapPrintingTechnique;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapProfile;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyCapSetDto {
    private String name;

    private Material material;

    private String color;

    @Enumerated(EnumType.STRING)
    private KeycapPrintingTechnique keyCabPrintingTechnique;

    @Enumerated(EnumType.STRING)
    private KeycapProfile keycapProfile;

    private LocalDate importedDate;

    private double price;

    private Integer quantity;

    private LocalDate soldOutDate;

    private int soldUnits;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String manufacturerName;
}
