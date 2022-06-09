package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.KeyboardBatch;
import com.axonactive.backEndFinalExam.service.dto.KeyboardBatchDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface KeyboardBatchMapper {
    KeyboardBatchMapper INSTANCE = Mappers.getMapper(KeyboardBatchMapper.class);


    @Mapping(source = "switchBatch.id",target = "switchBatchDto")
    @Mapping(source = "kitBatch.id",target = "kitBatchDto")
    @Mapping(source = "keyCapSet.id",target = "keyCapSetDto")


    KeyboardBatchDto toDto(KeyboardBatch keyboardBatch);
     List<KeyboardBatchDto> toDtos(List<KeyboardBatch> keyboardBatch);


}
