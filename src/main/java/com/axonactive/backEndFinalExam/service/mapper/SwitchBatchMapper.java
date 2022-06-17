package com.axonactive.backEndFinalExam.service.mapper;


import com.axonactive.backEndFinalExam.entity.SwitchBatch;

import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwitchBatchMapper {
    SwitchBatchMapper INSTANCE = Mappers.getMapper(SwitchBatchMapper.class);

    @Mapping(source = "manufacturer.name",target = "manufacturerName")

    SwitchBatchDto toDto(SwitchBatch switchBatch);

    List<SwitchBatchDto> toDtos(List<SwitchBatch> switchBatchList);
}
