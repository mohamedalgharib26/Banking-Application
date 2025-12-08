package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.AccountService;
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

    @Override
    public Optional<AccountRes> findById(UUID id) {
        return accountRepo.findById(id).map(this::convertToAccountRes);
    }

    @Override
    public List<AccountRes> findAll() {
        return accountRepo.findAll().stream()
                .map(this::convertToAccountRes)
                .collect(Collectors.toList());
    }

    @Override
    public AccountRes create(AccountReq request) {
        Account account = convertToAccount(request);
        Account savedAccount = accountRepo.save(account);
        return convertToAccountRes(savedAccount);
    }

    @Override
    public AccountRes update(UUID id, AccountReq request) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        updateAccountFromRequest(account, request);

        Account updatedAccount = accountRepo.save(account);
        return convertToAccountRes(updatedAccount);
    }

    @Override
    public void delete(UUID id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Optional<AccountRes> findByAccountNo(Integer accountNo) {
        Account account = accountRepo.findByAccountNo(accountNo);
        return account != null ? Optional.of(convertToAccountRes(account)) : Optional.empty();
    }

    @Override
    public List<AccountRes> findByBranchId(UUID branchId) {
        return accountRepo.findByBranchId(branchId).stream()
                .map(this::convertToAccountRes)
                .collect(Collectors.toList());
    }

    // Helper methods for conversion
    private AccountRes convertToAccountRes(Account account) {
        AccountRes response = new AccountRes();
        response.setId(account.getId());
        response.setAccountNo(account.getAccount_No());
        response.setAccType(account.getAcc_Type());
        response.setBalance(account.getBalance());
        response.setBranchId(account.getBranch() != null ? account.getBranch().getId() : null);
        response.setCreatedAt(account.getCreatedAt());
        response.setUpdatedAt(account.getUpdatedAt());
        return response;
    }

    private Account convertToAccount(AccountReq request) {
        Account account = new Account();
        account.setAccount_No(request.getAccountNo());
        account.setAcc_Type(request.getAccType());
        account.setBalance(request.getBalance());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            account.setBranch(branch);
        }

        return account;
    }

    private void updateAccountFromRequest(Account account, AccountReq request) {
        account.setAccount_No(request.getAccountNo());
        account.setAcc_Type(request.getAccType());
        account.setBalance(request.getBalance());

        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + request.getBranchId()));
            account.setBranch(branch);
        }
    }
}
