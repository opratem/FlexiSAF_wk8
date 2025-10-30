package com.flexisaf.FlexiSAF_wk8.controller;

import com.flexisaf.FlexiSAF_wk8.dto.ApiResponse;
import com.flexisaf.FlexiSAF_wk8.dto.EmployeeDTO;
import com.flexisaf.FlexiSAF_wk8.entity.Employee;
import com.flexisaf.FlexiSAF_wk8.exception.ResourceNotFoundException;
import com.flexisaf.FlexiSAF_wk8.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>("Employees retrieved successfully", employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok(new ApiResponse<>("Employee found", convertToDto(employee)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>("Email already exists", null));
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Employee created successfully", convertToDto(saved)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        BeanUtils.copyProperties(dto, employee, "id", "email");
        Employee updated = employeeRepository.save(employee);
        return ResponseEntity.ok(new ApiResponse<>("Employee updated successfully", convertToDto(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
        return ResponseEntity.ok(new ApiResponse<>("Employee deleted successfully", null));
    }

    private EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }
}
