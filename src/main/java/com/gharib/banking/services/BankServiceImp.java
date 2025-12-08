package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.BankService;
import com.gharib.banking.mappers.BankMapper;
import com.gharib.banking.models.Bank;
import com.gharib.banking.models.Dto.BankReq;
import com.gharib.banking.models.Dto.BankRes;
import com.gharib.banking.repos.BankRepository;

@Service
public class BankServiceImp implements BankService {

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private BankMapper bankMapper;

    @Override
    public Optional<BankRes> findById(UUID id) {
        return bankRepo.findById(id).map(bankMapper::toResponse);
    }

    @Override
    public List<BankRes> findAll() {
        return bankRepo.findAll().stream()
                .map(bankMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BankRes create(BankReq request) {
        Bank bank = bankMapper.toEntity(request);
        Bank savedBank = bankRepo.save(bank);
        return bankMapper.toResponse(savedBank);
    }

    @Override
    public BankRes update(UUID id, BankReq request) {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));

        bank.setName(request.getName());
        bank.setAddress(request.getAddress());

        Bank updatedBank = bankRepo.save(bank);
        return bankMapper.toResponse(updatedBank);
    }

    @Override
    public void delete(UUID id) {
        bankRepo.deleteById(id);
    }

    @Override
    public Optional<BankRes> findByName(String name) {
        Bank bank = bankRepo.findByName(name);
        return bank != null ? Optional.of(bankMapper.toResponse(bank)) : Optional.empty();
    }

    @Override
    public Optional<BankRes> findByAddress(String address) {
        Bank bank = bankRepo.findByAddress(address);
        return bank != null ? Optional.of(bankMapper.toResponse(bank)) : Optional.empty();
    }

    @Override
    public Optional<BankRes> findByNameAndAddress(String name, String address) {
        Bank bank = bankRepo.findByNameAndAddress(name, address);
        return bank != null ? Optional.of(bankMapper.toResponse(bank)) : Optional.empty();
    }
}
