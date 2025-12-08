package com.gharib.banking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.gharib.banking.models.Bank;
import com.gharib.banking.models.Dto.BankReq;
import com.gharib.banking.models.Dto.BankRes;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankMapper {

    BankRes toResponse(Bank bank);

    Bank toEntity(BankReq request);
}
