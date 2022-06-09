package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Manufacturer;
import com.axonactive.backEndFinalExam.service.dto.ManufacturerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ManufacturerMapper {
    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

    ManufacturerDto toDto(Manufacturer manufacturer);

    List<ManufacturerDto> toDtos(List<Manufacturer> manufacturerList);
}
