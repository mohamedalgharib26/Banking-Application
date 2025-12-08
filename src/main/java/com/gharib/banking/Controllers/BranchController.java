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

import com.gharib.banking.models.Dto.BranchReq;
import com.gharib.banking.models.Dto.BranchRes;
import com.gharib.banking.services.BranchServiceImp;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchServiceImp branchService;

    @GetMapping
    public List<BranchRes> getAllBranches() {
        return branchService.findAll();
    }

    @GetMapping("/search")
    public List<BranchRes> searchBranches(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) UUID bankId) {

        if (bankId != null) {
            return branchService.findByBankId(bankId);
        }

        if (name != null) {
            return branchService.findByName(name)
                    .map(List::of)
                    .orElse(List.of());
        }

        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<BranchRes> getBranchById(@PathVariable UUID id) {
        return branchService.findById(id);
    }

    @PostMapping
    public BranchRes createBranch(@RequestBody BranchReq branchReq) {
        return branchService.create(branchReq);
    }

    @PutMapping("/{id}")
    public BranchRes updateBranch(@PathVariable UUID id, @RequestBody BranchReq branchReq) {
        return branchService.update(id, branchReq);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable UUID id) {
        branchService.delete(id);
    }

}
