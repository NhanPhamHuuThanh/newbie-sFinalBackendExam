package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.service.dto.HousingDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface HousingMapper {
    HousingMapper INSTANCE = Mappers.getMapper(HousingMapper.class);


    HousingDto toDto(Housing housing);
    List<HousingDto> toDtos (List<Housing> housingList);
}
