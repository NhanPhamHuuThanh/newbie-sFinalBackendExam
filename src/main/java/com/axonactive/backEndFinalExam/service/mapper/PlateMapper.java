package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Plate;
import com.axonactive.backEndFinalExam.service.dto.PLateDto;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlateMapper {
    PlateMapper INSTANCE = Mappers.getMapper(PlateMapper.class);

    @Mapping(source = "kitBatch.id",target = "kitBatchId")

    PLateDto toDto(Plate plate);

    List<PLateDto> toDtos(List<Plate> plateList);

}
