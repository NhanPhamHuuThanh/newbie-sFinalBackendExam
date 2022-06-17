package com.axonactive.backEndFinalExam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchStringDto {
    
    private int springLength;

    private int springForce;

    private String springType;

    private int amountOfLiquidRecommended;

    private Integer switchBatchId;
}
