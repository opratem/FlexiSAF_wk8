package com.flexisaf.FlexiSAF_wk8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "First name is required")
    @Size (max = 50)
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(max = 50)
    private String lastName;

    @Email(message = "Invalid mail format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = 20)
    private String phoneNumber;

    private String department;

    private String positon;

    @PositiveOrZero(message = "Salary my be non-negative")
    private double salary;

    private String status; //FULL_TIME, PART_TIME, CONTRACT
    private boolean active;
    private String address;

}
