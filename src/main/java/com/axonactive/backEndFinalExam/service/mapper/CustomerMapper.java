package com.axonactive.backEndFinalExam.service.mapper;

import com.axonactive.backEndFinalExam.entity.Customer;
import com.axonactive.backEndFinalExam.service.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtos(List<Customer> customerList);

}
