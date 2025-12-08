package com.gharib.banking.Abstract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gharib.banking.models.Branch;
import com.gharib.banking.models.Dto.BranchReq;
import com.gharib.banking.models.Dto.BranchRes;

public interface BranchService extends BaseService<Branch, UUID, BranchReq, BranchRes> {

    Optional<BranchRes> findByName(String name);

    List<BranchRes> findByBankId(UUID bankId);

}
