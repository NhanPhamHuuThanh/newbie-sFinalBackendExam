package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import com.axonactive.backEndFinalExam.service.dto.KeyCapSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface KeycapMapper {
    KeycapMapper INSTANCE = Mappers.getMapper(KeycapMapper.class);

    @Mapping(source = "manufacturer.name",target = "manufacturerName")

    KeyCapSetDto toDto(KeyCapSet keyCapSet);

    List<KeyCapSetDto> toDtos(List<KeyCapSet> keyCapSetList);
}
