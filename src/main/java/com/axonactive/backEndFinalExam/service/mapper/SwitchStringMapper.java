package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.SwitchString;

import com.axonactive.backEndFinalExam.service.dto.SwitchStringDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwitchStringMapper {
    SwitchStringMapper INSTANCE = Mappers.getMapper(SwitchStringMapper.class);

    SwitchStringDto toDto(SwitchString stabAndKitBatch);

    List<SwitchStringDto> toDtos(List<SwitchString> switchStringList);

}
