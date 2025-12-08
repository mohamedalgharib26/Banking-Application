package com.gharib.banking.Abstract;

import java.util.List;
import java.util.UUID;

import com.gharib.banking.models.Loan;
import com.gharib.banking.models.Dto.LoanReq;
import com.gharib.banking.models.Dto.LoanRes;

public interface LoanService extends BaseService<Loan, UUID, LoanReq, LoanRes> {

    List<LoanRes> findByLoanType(String loanType);

    List<LoanRes> findByBranchId(UUID branchId);

}
