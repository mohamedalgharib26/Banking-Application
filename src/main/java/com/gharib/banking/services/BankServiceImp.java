package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.BankService;
import com.gharib.banking.models.Bank;
import com.gharib.banking.models.Dto.BankReq;
import com.gharib.banking.models.Dto.BankRes;
import com.gharib.banking.repos.BankRepository;

@Service
public class BankServiceImp implements BankService {

    @Autowired
    private BankRepository bankRepo;

    @Override
    public Optional<BankRes> findById(UUID id) {
        return bankRepo.findById(id).map(this::convertToBankRes);
    }

    @Override
    public List<BankRes> findAll() {
        return bankRepo.findAll().stream()
                .map(this::convertToBankRes)
                .collect(Collectors.toList());
    }

    @Override
    public BankRes create(BankReq request) {
        Bank bank = convertToBank(request);
        Bank savedBank = bankRepo.save(bank);
        return convertToBankRes(savedBank);
    }

    @Override
    public BankRes update(UUID id, BankReq request) {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));

        updateBankFromRequest(bank, request);

        Bank updatedBank = bankRepo.save(bank);
        return convertToBankRes(updatedBank);
    }

    @Override
    public void delete(UUID id) {
        bankRepo.deleteById(id);
    }

    // Helper methods for conversion
    private BankRes convertToBankRes(Bank bank) {
        BankRes response = new BankRes();
        response.setId(bank.getId());
        response.setName(bank.getName());
        response.setAddress(bank.getAddress());
        response.setCreatedAt(bank.getCreatedAt());
        response.setUpdatedAt(bank.getUpdatedAt());
        return response;
    }

    private Bank convertToBank(BankReq request) {
        Bank bank = new Bank();
        bank.setName(request.getName());
        bank.setAddress(request.getAddress());
        return bank;
    }

    private void updateBankFromRequest(Bank bank, BankReq request) {
        bank.setName(request.getName());
        bank.setAddress(request.getAddress());
    }

    @Override
    public Optional<BankRes> findByName(String name) {
        Bank bank = bankRepo.findByName(name);
        return bank != null ? Optional.of(convertToBankRes(bank)) : Optional.empty();
    }

    @Override
    public Optional<BankRes> findByAddress(String address) {
        Bank bank = bankRepo.findByAddress(address);
        return bank != null ? Optional.of(convertToBankRes(bank)) : Optional.empty();
    }

    @Override
    public Optional<BankRes> findByNameAndAddress(String name, String address) {
        Bank bank = bankRepo.findByNameAndAddress(name, address);
        return bank != null ? Optional.of(convertToBankRes(bank)) : Optional.empty();
    }
}
