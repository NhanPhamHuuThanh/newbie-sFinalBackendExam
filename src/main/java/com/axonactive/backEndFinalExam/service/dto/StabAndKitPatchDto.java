package com.axonactive.backEndFinalExam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StabAndKitPatchDto {

    private Integer kitBatchId;

    private Integer stabilizerId;

}
