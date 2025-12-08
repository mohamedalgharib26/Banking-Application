package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.LoanService;
import com.gharib.banking.mappers.LoanMapper;
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

    @Autowired
    private LoanMapper loanMapper;

    @Override
    public Optional<LoanRes> findById(UUID id) {
        return loanRepo.findById(id).map(loanMapper::toResponse);
    }

    @Override
    public List<LoanRes> findAll() {
        return loanRepo.findAll().stream()
                .map(loanMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanRes create(LoanReq request) {
        Loan loan = loanMapper.toEntity(request);

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            loan.setBranch(branch);
        }

        Loan savedLoan = loanRepo.save(loan);
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanRes update(UUID id, LoanReq request) {
        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        loan.setLoanType(request.getLoanType());
        loan.setAmount(request.getAmount());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            loan.setBranch(branch);
        }

        Loan updatedLoan = loanRepo.save(loan);
        return loanMapper.toResponse(updatedLoan);
    }

    @Override
    public void delete(UUID id) {
        loanRepo.deleteById(id);
    }

    @Override
    public List<LoanRes> findByLoanType(String loanType) {
        return loanRepo.findByLoanType(loanType).stream()
                .map(loanMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanRes> findByBranchId(UUID branchId) {
        return loanRepo.findByBranchId(branchId).stream()
                .map(loanMapper::toResponse)
                .collect(Collectors.toList());
    }
}
