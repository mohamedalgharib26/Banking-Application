package com.gharib.banking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.gharib.banking.models.Loan;
import com.gharib.banking.models.Dto.LoanReq;
import com.gharib.banking.models.Dto.LoanRes;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {

    @Mapping(source = "branch.id", target = "branchId")
    LoanRes toResponse(Loan loan);

    @Mapping(source = "branchId", target = "branch.id")
    Loan toEntity(LoanReq request);
}
