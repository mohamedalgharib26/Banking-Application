package com.gharib.banking.Abstract;

import java.util.Optional;
import java.util.UUID;

import com.gharib.banking.models.Customer;
import com.gharib.banking.models.Dto.CustomerReq;
import com.gharib.banking.models.Dto.CustomerRes;

public interface CustomerService extends BaseService<Customer, UUID, CustomerReq, CustomerRes> {

    Optional<CustomerRes> findByName(String name);

    Optional<CustomerRes> findByPhone(Integer phone);

}
