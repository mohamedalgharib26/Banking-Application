package com.gharib.banking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.gharib.banking.models.Account;
import com.gharib.banking.models.Dto.AccountReq;
import com.gharib.banking.models.Dto.AccountRes;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(source = "account_No", target = "accountNo")
    @Mapping(source = "acc_Type", target = "accType")
    @Mapping(source = "branch.id", target = "branchId")
    AccountRes toResponse(Account account);

    @Mapping(source = "accountNo", target = "account_No")
    @Mapping(source = "accType", target = "acc_Type")
    @Mapping(source = "branchId", target = "branch.id")
    Account toEntity(AccountReq request);
}
