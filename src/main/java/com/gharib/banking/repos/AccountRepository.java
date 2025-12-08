package com.gharib.banking.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gharib.banking.Abstract.BaseRepo;
import com.gharib.banking.models.Account;

@Repository
public interface AccountRepository extends BaseRepo<Account, UUID> {

    Account findByAccountNo(Integer accountNo);

    List<Account> findByBranchId(UUID branchId);
}
