package com.gharib.banking.models.Dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchReq {

    @NotBlank(message = "Branch name is required")
    @Size(min = 2, max = 100, message = "Branch name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Branch address is required")
    @Size(min = 5, max = 200, message = "Branch address must be between 5 and 200 characters")
    private String address;

    @NotNull(message = "Bank ID is required")
    private UUID bankId;
}
