package com.gharib.banking.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gharib.banking.models.Dto.AccountReq;
import com.gharib.banking.models.Dto.AccountRes;
import com.gharib.banking.services.AccountServiceImp;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImp accountService;

    @GetMapping
    public List<AccountRes> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/search")
    public List<AccountRes> searchAccounts(
            @RequestParam(required = false) Integer accountNo,
            @RequestParam(required = false) UUID branchId) {

        if (branchId != null) {
            return accountService.findByBranchId(branchId);
        }

        if (accountNo != null) {
            return accountService.findByAccountNo(accountNo)
                    .map(List::of)
                    .orElse(List.of());
        }

        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AccountRes> getAccountById(@PathVariable UUID id) {
        return accountService.findById(id);
    }

    @PostMapping
    public AccountRes createAccount(@RequestBody AccountReq accountReq) {
        return accountService.create(accountReq);
    }

    @PutMapping("/{id}")
    public AccountRes updateAccount(@PathVariable UUID id, @RequestBody AccountReq accountReq) {
        return accountService.update(id, accountReq);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        accountService.delete(id);
    }

}
