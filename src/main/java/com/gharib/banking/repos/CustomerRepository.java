package com.gharib.banking.repos;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gharib.banking.Abstract.BaseRepo;
import com.gharib.banking.models.Customer;

@Repository
public interface CustomerRepository extends BaseRepo<Customer, UUID> {

    Customer findByName(String name);

    Customer findByPhone(Integer phone);
}
