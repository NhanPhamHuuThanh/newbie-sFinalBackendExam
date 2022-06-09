package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Pcb;
import com.axonactive.backEndFinalExam.service.dto.PcbDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PcbMapper {
    PcbMapper INSTANCE = Mappers.getMapper(PcbMapper.class);

    PcbDto toDto(Pcb pcb);
    List<PcbDto> toDtos(List<Pcb> pcbList);

}
