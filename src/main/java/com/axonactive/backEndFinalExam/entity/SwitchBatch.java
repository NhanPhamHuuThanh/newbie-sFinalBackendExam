package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwitchBatch {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SwitchType switchType;

    private int quantity;

    private int soldUnits;

    private double pricePerUnit;

    private LocalDate importedDate;

    private String switchName;

    private LocalDate soldOutDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn
    private Manufacturer manufacturer;


}
