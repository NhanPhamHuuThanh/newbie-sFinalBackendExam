package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardBatchDto {

    private double totalPrice;

    private String name;

    private String insuranceWarranty;

    private LocalDate assembleDate;

    private Integer switchBatchDto;

    private Integer kitBatchDto;

    private Integer keyCapSetDto;


}
