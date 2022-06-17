package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Stem;

import com.axonactive.backEndFinalExam.service.dto.StemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StemMapper {
    StemMapper INSTANCE = Mappers.getMapper(StemMapper.class);

    @Mapping(source = "switchBatch.id",target = "switchBatchId")

    StemDto toDto(Stem stem);

    List<StemDto> toDtos(List<Stem> stems);

}
