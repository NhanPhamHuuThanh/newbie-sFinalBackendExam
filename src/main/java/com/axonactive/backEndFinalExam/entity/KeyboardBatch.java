package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyboardBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int insuranceWarrantyMonth;

    private int quantity;

    private int soldUnits;

    private LocalDate soldOutDate;

    private LocalDate importedDate;

    private double pricePerUnit;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn
    @ManyToOne
    private Manufacturer manufacturer;

    private String kitBatchName;

    private String switchBatchName;

    private String keyCapName;
}

