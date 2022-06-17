package com.axonactive.backEndFinalExam.api.request;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchBatchRequest {
    @Enumerated(EnumType.STRING)
    private SwitchType switchType;

    private int quantity;


    private double pricePerUnit;

    private LocalDate importedDate;

    private String switchName;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String manufacturerName;
}
