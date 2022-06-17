package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StemDto {

    private int stemHeight;

    private Material material;

    private String stemColor;

    private Integer switchBatchId;
}
