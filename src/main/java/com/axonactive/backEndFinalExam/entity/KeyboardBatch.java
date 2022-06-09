package com.axonactive.backEndFinalExam.entity;

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

    private double totalPrice;

    private String name;

    private String insuranceWarranty;

    private LocalDate assembleDate;

    @JoinColumn
    @ManyToOne
    private SwitchBatch switchBatch;

    @JoinColumn
    @ManyToOne
    private KitBatch kitBatch;


    @JoinColumn
    @ManyToOne
    private KeyCapSet keyCapSet;
}
