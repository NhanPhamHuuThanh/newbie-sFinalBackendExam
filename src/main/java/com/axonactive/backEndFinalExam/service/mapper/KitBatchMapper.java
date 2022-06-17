package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.KitBatch;

import com.axonactive.backEndFinalExam.service.dto.KitBatchDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface KitBatchMapper {
    KitBatchMapper INSTANCE = Mappers.getMapper(KitBatchMapper.class);

    @Mapping(source = "manufacturer.name",target = "manufacturerName")

    KitBatchDto toDto(KitBatch kitBatch);

    List<KitBatchDto> toDtos(List<KitBatch> kitBatchList);
}
