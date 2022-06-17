package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Material;
import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Plate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Enumerated(EnumType.STRING)
    private MountType mountType;

    @JoinColumn
    @ManyToOne
    private KitBatch kitBatch;
}
