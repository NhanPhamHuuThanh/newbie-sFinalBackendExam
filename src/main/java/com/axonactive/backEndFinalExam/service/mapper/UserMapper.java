package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.User;
import com.axonactive.backEndFinalExam.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User employee);

    List<UserDto> toDtos (List<User> Employee);
}
