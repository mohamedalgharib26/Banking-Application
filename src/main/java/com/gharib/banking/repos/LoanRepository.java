package com.gharib.banking.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gharib.banking.Abstract.BaseRepo;
import com.gharib.banking.models.Loan;

@Repository
public interface LoanRepository extends BaseRepo<Loan, UUID> {

    List<Loan> findByLoanType(String loanType);

    List<Loan> findByBranchId(UUID branchId);
}
