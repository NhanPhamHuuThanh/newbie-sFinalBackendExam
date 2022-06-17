package com.axonactive.backEndFinalExam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {

    private String name;

    private String location;

    private String email;

    private Integer keyCapSetId;

    private Integer kitBatchId;

    private Integer switchBatchId;
}
