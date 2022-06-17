package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.PcbCut;
import com.axonactive.backEndFinalExam.entity.enumClazz.pcbType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcbDto {
    private pcbType pcbType;

    private Material material;

    private PcbCut pcbCut;

    private Integer kitBatchId;
}
