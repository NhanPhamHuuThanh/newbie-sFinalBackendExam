package com.axonactive.backEndFinalExam.service.dto;
import com.axonactive.backEndFinalExam.entity.enumClazz.MountType;
import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchBatchDto {
    private SwitchType switchType;

    private double pricePerUnit;

    private String switchName;

    private int amountOfLubeForSwitch;

    private int stemHeight;

    private int springLength;

    private int springForce;

    private MountType mountType;

    private String color;

    private String manufacturerName;
}

