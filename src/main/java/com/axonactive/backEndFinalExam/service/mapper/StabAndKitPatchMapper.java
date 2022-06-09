package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.StabAndKitBatch;
import com.axonactive.backEndFinalExam.service.dto.StabAndKitPatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface StabAndKitPatchMapper {
    StabAndKitPatchMapper INSTANCE = Mappers.getMapper(StabAndKitPatchMapper.class);
    @Mapping(source = "kitBatch.id",target ="kitBatchId" )
    @Mapping(source = "stabilizer.id",target ="stabilizerId" )
    StabAndKitPatchDto toDto(StabAndKitBatch stabAndKitBatch);
    List<StabAndKitPatchDto> toDtos(List<StabAndKitBatch> stabAndKitPatchDtoList);

}
