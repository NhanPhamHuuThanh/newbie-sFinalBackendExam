package com.axonactive.backEndFinalExam.api.request;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapPrintingTechnique;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapProfile;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyCapSetRequest {
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

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String manufacturerName;
}
