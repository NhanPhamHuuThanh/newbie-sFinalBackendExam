package com.axonactive.backEndFinalExam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.CodeSigner;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StabAndKitPatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private KitPatch kitPatch;

    @JoinColumn
    @ManyToOne
    private Stabilizer stabilizer;
}
