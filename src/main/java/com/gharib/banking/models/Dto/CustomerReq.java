package com.gharib.banking.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReq {

    private String name;

    private String address;

    private Integer phone;
}
