package com.axonactive.backEndFinalExam.api.employeeapi;


import com.axonactive.backEndFinalExam.Exception.ResourceNotFoundException;

import com.axonactive.backEndFinalExam.entity.User;
import com.axonactive.backEndFinalExam.service.UserService;
import com.axonactive.backEndFinalExam.service.dto.UserDto;
import com.axonactive.backEndFinalExam.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(UserResource.PATH)
public class UserResource {
    public static final String PATH = "/api/employees";
    @Autowired
    private UserService employeeService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getALL() {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDtos(employeeService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        User employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for employee"));
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(employee));
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewCase(@RequestBody User employee) {
        User employeeCreated = employeeService.save(employee);
        return ResponseEntity.created(URI.create(UserResource.PATH + "/" + employee.getId()))
                .body(UserMapper.INSTANCE.toDto(employeeCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateCase(@PathVariable(value = "id") Integer id, @RequestBody UserDto employee) throws ResourceNotFoundException {
        User updateUser = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for employee"));
       updateUser.setAddress(employee.getAddress());
       updateUser.setEmail(employee.getEmail());
       updateUser.setName(employee.getName());
       updateUser.setPhoneNumber(employee.getPhoneNumber());
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(employeeService.save(updateUser)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        User deleteUser = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for employee"));
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
