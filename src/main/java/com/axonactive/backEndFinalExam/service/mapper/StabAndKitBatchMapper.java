package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;
import com.axonactive.backEndFinalExam.service.dto.StabAndKitBatchDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface StabAndKitBatchMapper {
    StabAndKitBatchMapper INSTANCE = Mappers.getMapper(StabAndKitBatchMapper.class);
    @Mapping(source = "kitBatch.id",target ="kitBatchId" )
    @Mapping(source = "stabilizer.id",target ="stabilizerId" )
    StabAndKitBatchDto toDto(StabAndKitBatch stabAndKitBatch);

    List<StabAndKitBatchDto> toDtos(List<StabAndKitBatch> stabAndKitPatchDtoList);

}
