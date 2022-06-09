package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Caze;
import com.axonactive.backEndFinalExam.service.dto.CaseDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CaseMapper {
    CaseMapper INSTANCE = Mappers.getMapper(CaseMapper.class);

    CaseDto toDto(Caze case1);

    List<CaseDto> toDtos(List<Caze> caseList);
}
