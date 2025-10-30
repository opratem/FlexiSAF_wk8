package com.flexisaf.FlexiSAF_wk8.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {
    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private String reason;
    private String managerComment;
    private LocalDate dateApplied;
    private LocalDate dateReviewed;

}
