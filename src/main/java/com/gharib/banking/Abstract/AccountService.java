package com.gharib.banking.Abstract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gharib.banking.models.Account;
import com.gharib.banking.models.Dto.AccountReq;
import com.gharib.banking.models.Dto.AccountRes;

public interface AccountService extends BaseService<Account, UUID, AccountReq, AccountRes> {

    Optional<AccountRes> findByAccountNo(Integer accountNo);

    List<AccountRes> findByBranchId(UUID branchId);

}
