package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.SwitchSpring;
import com.axonactive.backEndFinalExam.service.dto.SwitchStringDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwitchStringMapper {
    SwitchStringMapper INSTANCE = Mappers.getMapper(SwitchStringMapper.class);

    @Mapping(source = "switchBatch.id",target = "switchBatchId")

    SwitchStringDto toDto(SwitchSpring stabAndKitBatch);

    List<SwitchStringDto> toDtos(List<SwitchSpring> switchStringList);

}
