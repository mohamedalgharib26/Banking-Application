package com.gharib.banking.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gharib.banking.models.Dto.BankReq;
import com.gharib.banking.models.Dto.BankRes;
import com.gharib.banking.services.BankServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankServiceImp bankService;

    @GetMapping
    public List<BankRes> getAllBanks() {
        return bankService.findAll();
    }

    @GetMapping("/search")
    public List<BankRes> searchBanks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {

        // If both name and address are provided, search by both (AND condition)
        if (name != null && address != null) {
            return bankService.findByNameAndAddress(name, address)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }

        // If only name query parameter is provided
        if (name != null) {
            return bankService.findByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }

        // If only address query parameter is provided
        if (address != null) {
            return bankService.findByAddress(address)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }

        // If no parameters provided, return all banks
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<BankRes> getBankById(@PathVariable UUID id) {
        return bankService.findById(id);
    }

    @PostMapping
    public BankRes createBank(@Valid @RequestBody BankReq bankReq) {
        return bankService.create(bankReq);
    }

    @PutMapping("/{id}")
    public BankRes updateBank(@PathVariable UUID id, @Valid @RequestBody BankReq bankReq) {
        return bankService.update(id, bankReq);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable UUID id) {
        bankService.delete(id);
    }

}
