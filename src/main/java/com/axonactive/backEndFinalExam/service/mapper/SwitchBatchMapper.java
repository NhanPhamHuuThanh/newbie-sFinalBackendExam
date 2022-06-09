package com.axonactive.backEndFinalExam.service.mapper;


import com.axonactive.backEndFinalExam.entity.SwitchBatch;

import com.axonactive.backEndFinalExam.service.dto.SwitchBatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwitchBatchMapper {
    SwitchStringMapper INSTANCE = Mappers.getMapper(SwitchStringMapper.class);

    @Mapping(source = "stem.stemHeight",target = "stemHeight")
    @Mapping(source = "switchString.springLength",target = "springLength")
    @Mapping(source = "switchString.springForce", target = "springForce")
    @Mapping(source = "housing.mountType",target = "mountType")
    @Mapping(source = "housing.color",target = "color")
    @Mapping(source = "manufacturer.name",target = "manufacturerName")
    SwitchBatchDto toDto(SwitchBatch switchBatch);
    List<SwitchBatchDto> toDtos(List<SwitchBatch> switchBatchList);
}
