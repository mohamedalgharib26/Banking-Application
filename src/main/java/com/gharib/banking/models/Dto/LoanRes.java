package com.gharib.banking.models.Dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRes {

    private UUID id;

    private String loanType;

    private Integer amount;

    private UUID branchId;

    private Date createdAt;

    private Date updatedAt;
}
