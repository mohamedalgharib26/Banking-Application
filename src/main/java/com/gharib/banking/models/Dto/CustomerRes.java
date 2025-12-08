package com.gharib.banking.models.Dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRes {

    private UUID id;

    private String name;

    private String address;

    private Integer phone;

    private Date createdAt;

    private Date updatedAt;
}
