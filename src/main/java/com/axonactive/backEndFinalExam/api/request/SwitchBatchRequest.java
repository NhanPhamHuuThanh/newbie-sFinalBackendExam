package com.axonactive.backEndFinalExam.api.request;

import com.axonactive.backEndFinalExam.entity.enumClazz.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.util.StringSwitcher;

import javax.management.StandardEmitterMBean;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchBatchRequest {
    private Integer id;

    private SwitchType switchType;

    private int quantity;

    private double pricePerUnit;

    private LocalDate importedDate;

    private String switchName;

    private int amountOfLubeForSwitch;


    private Integer stemId;

    private Integer switchStringId;

    private Integer housingId;

    private Integer manufacturerId;

}
