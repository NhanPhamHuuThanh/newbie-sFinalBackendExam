package com.axonactive.backEndFinalExam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pcb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated
    private pcbType pcbType;

    @Enumerated
    private Material material;

    @Enumerated
    private PcbCut pcbCut;
}
