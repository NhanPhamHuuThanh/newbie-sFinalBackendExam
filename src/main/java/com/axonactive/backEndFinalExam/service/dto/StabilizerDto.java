package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StabilizerDto {

    private Material material;

    private int length;

    private String stabStatus;
}
