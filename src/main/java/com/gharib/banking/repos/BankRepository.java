package com.gharib.banking.repos;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gharib.banking.Abstract.BaseRepo;
import com.gharib.banking.models.Bank;

@Repository
public interface BankRepository extends BaseRepo<Bank, UUID> {
    Bank findByName(String name);

    Bank findByAddress(String address);

    Bank findByNameAndAddress(String name, String address);
}
