package com.axonactive.backEndFinalExam.service.dto;

import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardBatchDto {
    private String name;

    private int insuranceWarrantyMonth;

    private int quantity;

    private int soldUnits;

    private LocalDate soldDate;

    private LocalDate importedDate;

    private double pricePerUnit;

    private Status status;

    private String manufacturerName;

    private String kitBatchName;

    private String switchName;

    private String keyCapName;
}
