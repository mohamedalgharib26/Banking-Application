package com.gharib.banking.models.Dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanReq {

    private String loanType;

    private Integer amount;

    private UUID branchId;
}
