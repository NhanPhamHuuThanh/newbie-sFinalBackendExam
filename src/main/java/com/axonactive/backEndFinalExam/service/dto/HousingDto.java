package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HousingDto {

    private Material material;

    private MountType mountType;

    private String color;

    private Integer switchBatchId;
}
