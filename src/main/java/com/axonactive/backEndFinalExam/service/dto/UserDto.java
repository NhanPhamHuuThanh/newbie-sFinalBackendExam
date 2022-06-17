package com.axonactive.backEndFinalExam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;

    private String phoneNumber;

    private String address;

    private String email;

}
