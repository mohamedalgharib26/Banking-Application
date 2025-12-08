package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.AccountService;
import com.gharib.banking.mappers.AccountMapper;
import com.gharib.banking.models.Account;
import com.gharib.banking.models.Branch;
import com.gharib.banking.models.Dto.AccountReq;
import com.gharib.banking.models.Dto.AccountRes;
import com.gharib.banking.repos.AccountRepository;
import com.gharib.banking.repos.BranchRepository;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Optional<AccountRes> findById(UUID id) {
        return accountRepo.findById(id).map(accountMapper::toResponse);
    }

    @Override
    public List<AccountRes> findAll() {
        return accountRepo.findAll().stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountRes create(AccountReq request) {
        Account account = accountMapper.toEntity(request);

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            account.setBranch(branch);
        }

        Account savedAccount = accountRepo.save(account);
        return accountMapper.toResponse(savedAccount);
    }

    @Override
    public AccountRes update(UUID id, AccountReq request) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        account.setAccount_No(request.getAccountNo());
        account.setAcc_Type(request.getAccType());
        account.setBalance(request.getBalance());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            account.setBranch(branch);
        }

        Account updatedAccount = accountRepo.save(account);
        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public void delete(UUID id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Optional<AccountRes> findByAccountNo(Integer accountNo) {
        Account account = accountRepo.findByAccountNo(accountNo);
        return account != null ? Optional.of(accountMapper.toResponse(account)) : Optional.empty();
    }

    @Override
    public List<AccountRes> findByBranchId(UUID branchId) {
        return accountRepo.findByBranchId(branchId).stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }
}
