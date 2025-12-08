package com.gharib.banking.models.Dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchReq {

    private String name;

    private String address;

    private UUID bankId;
}
