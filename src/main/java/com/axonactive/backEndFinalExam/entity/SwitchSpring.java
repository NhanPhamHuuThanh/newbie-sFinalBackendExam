package com.axonactive.backEndFinalExam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwitchSpring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int springLength;

    private int springForce;

    private String springType;

    private int amountOfLiquidRecommended;

    @JoinColumn
    @ManyToOne
    private SwitchBatch switchBatch;
}
