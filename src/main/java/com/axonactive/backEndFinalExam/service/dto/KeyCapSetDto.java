package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapPrintingTechnique;
import com.axonactive.backEndFinalExam.entity.enumClazz.KeycapProfile;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyCapSetDto {
    private Material material;

    private String color;

    private KeycapPrintingTechnique keyCabPrintingTechnique;

    private KeycapProfile keycapProfile;

    private String manufacturerName;



    private double price;
}
