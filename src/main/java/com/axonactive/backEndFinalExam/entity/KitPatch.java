package com.axonactive.backEndFinalExam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitPatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String layOut;

    private String size;

    private LocalDate manufacturedDate;

    private String model;

    private int quantity;

    private LocalDate importedDate;

    private double pricePerUnit;

    @JoinColumn
    @ManyToOne
    private Plate plate;

    @JoinColumn
    @ManyToOne
    private Case aCase;

    @JoinColumn
    @ManyToOne
    private Pcb pcb;

    @JoinColumn
    @ManyToOne
    private Manufacturer manufacturer;




}