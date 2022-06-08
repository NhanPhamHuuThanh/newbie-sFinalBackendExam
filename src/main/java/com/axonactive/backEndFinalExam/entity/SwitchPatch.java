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
public class SwitchPatch {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated
    private SwitchType switchType;

    private int quantity;

    private double pricePerUnit;

    private LocalDate importedDate;

    private String switchName;

    private int amountOfLubeForSwitch;

    @JoinColumn
    @ManyToOne
    private Stem stem;

    @JoinColumn
    @ManyToOne
    private SwitchString switchString;

    @JoinColumn
    @ManyToOne
    private Housing housing;

    @JoinColumn
    @ManyToOne
    private Manufacturer manufacturer;
}
