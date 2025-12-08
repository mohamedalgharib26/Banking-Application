package com.gharib.banking.Abstract;

import java.util.Optional;
import java.util.UUID;

import com.gharib.banking.models.Bank;
import com.gharib.banking.models.Dto.BankReq;
import com.gharib.banking.models.Dto.BankRes;

public interface BankService extends BaseService<Bank, UUID, BankReq, BankRes> {

    Optional<BankRes> findByName(String name);

    Optional<BankRes> findByAddress(String address);

    Optional<BankRes> findByNameAndAddress(String name, String address);

}
