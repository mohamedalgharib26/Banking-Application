package com.gharib.banking.models.Dto;

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
public class CustomerReq {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Customer address is required")
    @Size(min = 5, max = 200, message = "Customer address must be between 5 and 200 characters")
    private String address;

    @NotNull(message = "Phone number is required")
    @Min(value = 1, message = "Phone number must be positive")
    private Integer phone;
}
