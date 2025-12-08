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

import com.gharib.banking.models.Dto.LoanReq;
import com.gharib.banking.models.Dto.LoanRes;
import com.gharib.banking.services.LoanServiceImp;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanServiceImp loanService;

    @GetMapping
    public List<LoanRes> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/search")
    public List<LoanRes> searchLoans(
            @RequestParam(required = false) String loanType,
            @RequestParam(required = false) UUID branchId) {

        if (branchId != null) {
            return loanService.findByBranchId(branchId);
        }

        if (loanType != null) {
            return loanService.findByLoanType(loanType);
        }

        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LoanRes> getLoanById(@PathVariable UUID id) {
        return loanService.findById(id);
    }

    @PostMapping
    public LoanRes createLoan(@RequestBody LoanReq loanReq) {
        return loanService.create(loanReq);
    }

    @PutMapping("/{id}")
    public LoanRes updateLoan(@PathVariable UUID id, @RequestBody LoanReq loanReq) {
        return loanService.update(id, loanReq);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable UUID id) {
        loanService.delete(id);
    }

}
