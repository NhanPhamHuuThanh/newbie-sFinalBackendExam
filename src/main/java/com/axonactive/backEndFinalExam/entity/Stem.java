package com.axonactive.backEndFinalExam.entity;

import com.sun.istack.Interned;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int stemHeight;

    @Enumerated
    private Material material;

    private String stemColor;
}
