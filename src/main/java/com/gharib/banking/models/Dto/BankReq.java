package com.gharib.banking.models.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankReq {

    @NotBlank(message = "Bank name is required")
    @Size(min = 2, max = 100, message = "Bank name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Bank address is required")
    @Size(min = 5, max = 200, message = "Bank address must be between 5 and 200 characters")
    private String address;
}
