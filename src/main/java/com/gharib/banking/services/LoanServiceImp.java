package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.LoanService;
import com.gharib.banking.models.Branch;
import com.gharib.banking.models.Loan;
import com.gharib.banking.models.Dto.LoanReq;
import com.gharib.banking.models.Dto.LoanRes;
import com.gharib.banking.repos.BranchRepository;
import com.gharib.banking.repos.LoanRepository;

@Service
public class LoanServiceImp implements LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Override
    public Optional<LoanRes> findById(UUID id) {
        return loanRepo.findById(id).map(this::convertToLoanRes);
    }

    @Override
    public List<LoanRes> findAll() {
        return loanRepo.findAll().stream()
                .map(this::convertToLoanRes)
                .collect(Collectors.toList());
    }

    @Override
    public LoanRes create(LoanReq request) {
        Loan loan = convertToLoan(request);
        Loan savedLoan = loanRepo.save(loan);
        return convertToLoanRes(savedLoan);
    }

    @Override
    public LoanRes update(UUID id, LoanReq request) {
        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        updateLoanFromRequest(loan, request);

        Loan updatedLoan = loanRepo.save(loan);
        return convertToLoanRes(updatedLoan);
    }

    @Override
    public void delete(UUID id) {
        loanRepo.deleteById(id);
    }

    @Override
    public List<LoanRes> findByLoanType(String loanType) {
        return loanRepo.findByLoanType(loanType).stream()
                .map(this::convertToLoanRes)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanRes> findByBranchId(UUID branchId) {
        return loanRepo.findByBranchId(branchId).stream()
                .map(this::convertToLoanRes)
                .collect(Collectors.toList());
    }

    // Helper methods for conversion
    private LoanRes convertToLoanRes(Loan loan) {
        LoanRes response = new LoanRes();
        response.setId(loan.getId());
        response.setLoanType(loan.getLoanType());
        response.setAmount(loan.getAmount());
        response.setBranchId(loan.getBranch() != null ? loan.getBranch().getId() : null);
        response.setCreatedAt(loan.getCreatedAt());
        response.setUpdatedAt(loan.getUpdatedAt());
        return response;
    }

    private Loan convertToLoan(LoanReq request) {
        Loan loan = new Loan();
        loan.setLoanType(request.getLoanType());
        loan.setAmount(request.getAmount());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            loan.setBranch(branch);
        }

        return loan;
    }

    private void updateLoanFromRequest(Loan loan, LoanReq request) {
        loan.setLoanType(request.getLoanType());
        loan.setAmount(request.getAmount());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            loan.setBranch(branch);
        }
    }
}
