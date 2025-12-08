package com.gharib.banking.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gharib.banking.Abstract.BaseRepo;
import com.gharib.banking.models.Branch;

@Repository
public interface BranchRepository extends BaseRepo<Branch, UUID> {

    Branch findByName(String name);

    List<Branch> findByBankId(UUID bankId);
}
