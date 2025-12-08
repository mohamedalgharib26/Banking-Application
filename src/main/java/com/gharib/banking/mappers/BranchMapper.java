package com.gharib.banking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.gharib.banking.models.Branch;
import com.gharib.banking.models.Dto.BranchReq;
import com.gharib.banking.models.Dto.BranchRes;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BranchMapper {

    @Mapping(source = "bank.id", target = "bankId")
    BranchRes toResponse(Branch branch);

    @Mapping(source = "bankId", target = "bank.id")
    Branch toEntity(BranchReq request);
}
