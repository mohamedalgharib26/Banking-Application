package com.gharib.banking.models.Dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanReq {

    @NotBlank(message = "Loan type is required")
    @Size(min = 2, max = 50, message = "Loan type must be between 2 and 50 characters")
    private String loanType;

    @NotNull(message = "Loan amount is required")
    @Min(value = 1, message = "Loan amount must be positive")
    private Integer amount;

    @NotNull(message = "Branch ID is required")
    private UUID branchId;
}
