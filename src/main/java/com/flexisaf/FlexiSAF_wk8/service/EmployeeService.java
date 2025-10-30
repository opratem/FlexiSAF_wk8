package com.flexisaf.FlexiSAF_wk8.service;

import com.flexisaf.FlexiSAF_wk8.dto.EmployeeRequestDTO;
import com.flexisaf.FlexiSAF_wk8.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Employee createEmployee(EmployeeRequestDTO dto);
    Employee getEmployeeById(Long id);
    Page<Employee> getEmployees(Pageable pageable);
    Employee  updateEmployee(Long id, EmployeeRequestDTO dto);
    void deleteEmployee(Long id);
}
