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
public class AccountReq {

    @NotNull(message = "Account number is required")
    @Min(value = 1, message = "Account number must be positive")
    private Integer accountNo;

    @NotBlank(message = "Account type is required")
    @Size(min = 2, max = 50, message = "Account type must be between 2 and 50 characters")
    private String accType;

    @NotNull(message = "Balance is required")
    @Min(value = 0, message = "Balance cannot be negative")
    private Integer balance;

    @NotNull(message = "Branch ID is required")
    private UUID branchId;
}
