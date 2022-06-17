package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.CustomKeyboard;
import com.axonactive.backEndFinalExam.entity.Housing;
import com.axonactive.backEndFinalExam.service.dto.CustomKeyboardDto;
import com.axonactive.backEndFinalExam.service.dto.HousingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomKeyboardMapper {

    CustomKeyboardMapper INSTANCE = Mappers.getMapper(CustomKeyboardMapper.class);

    CustomKeyboardDto toDto(CustomKeyboard customKeyboard);

    List<CustomKeyboardDto> toDtos (List<CustomKeyboard> customKeyboardList);
}
