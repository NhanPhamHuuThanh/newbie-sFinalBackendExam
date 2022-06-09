package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Stabilizer;
import com.axonactive.backEndFinalExam.service.dto.StabilizerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StabilizerMapper {
    StabilizerMapper INSTANCE = Mappers.getMapper(StabilizerMapper.class);

    StabilizerDto toDto(Stabilizer stab );
    List<StabilizerDto> toDtos(List<Stabilizer> stabList);

}
