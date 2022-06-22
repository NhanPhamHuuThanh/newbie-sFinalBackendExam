package com.axonactive.backEndFinalExam.security.service;

import com.axonactive.backEndFinalExam.security.service.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

}
