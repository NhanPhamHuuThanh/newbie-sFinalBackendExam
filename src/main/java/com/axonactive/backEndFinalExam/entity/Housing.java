package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
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
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Enumerated(EnumType.STRING)
    private MountType mountType;

    private String color;

    @JoinColumn
    @ManyToOne
    private SwitchBatch switchBatch;
}
