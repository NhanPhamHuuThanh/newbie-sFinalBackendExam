package com.axonactive.backEndFinalExam.entity;

import com.axonactive.backEndFinalExam.entity.enumClazz.Payment;
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
public class CustomKeyboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String switchName;

    private String kitModel;

    private String keyCapSet;

    private double assemblePrice;

    private String  color;

    private LocalDate expectedDeliveryDate;

    private LocalDate actualDeliveryDate;

    private final int insuranceEnd = 1;

    public static final double assembleRate = 1.2;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @ManyToOne
    @JoinColumn
    private Customer customer;
}
