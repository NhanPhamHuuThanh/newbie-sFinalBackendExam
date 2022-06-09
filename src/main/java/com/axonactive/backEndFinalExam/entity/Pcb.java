package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.PcbCut;
import com.axonactive.backEndFinalExam.entity.enumClazz.pcbType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
