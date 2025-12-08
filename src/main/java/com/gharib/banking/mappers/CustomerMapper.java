package com.gharib.banking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.gharib.banking.models.Customer;
import com.gharib.banking.models.Dto.CustomerReq;
import com.gharib.banking.models.Dto.CustomerRes;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    CustomerRes toResponse(Customer customer);

    Customer toEntity(CustomerReq request);
}
