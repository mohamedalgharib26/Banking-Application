package com.gharib.banking.models.Dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountReq {

    private Integer accountNo;

    private String accType;

    private Integer balance;

    private UUID branchId;
}
