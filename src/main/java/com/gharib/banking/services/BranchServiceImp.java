package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.BranchService;
import com.gharib.banking.mappers.BranchMapper;
import com.gharib.banking.models.Bank;
import com.gharib.banking.models.Branch;
import com.gharib.banking.models.Dto.BranchReq;
import com.gharib.banking.models.Dto.BranchRes;
import com.gharib.banking.repos.BankRepository;
import com.gharib.banking.repos.BranchRepository;

@Service
public class BranchServiceImp implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private BranchMapper branchMapper;

    @Override
    public Optional<BranchRes> findById(UUID id) {
        return branchRepo.findById(id).map(branchMapper::toResponse);
    }

    @Override
    public List<BranchRes> findAll() {
        return branchRepo.findAll().stream()
                .map(branchMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BranchRes create(BranchReq request) {
        Branch branch = branchMapper.toEntity(request);

        if (request.getBankId() != null) {
            Bank bank = bankRepo.findById(request.getBankId())
                    .orElseThrow(() -> new RuntimeException("Bank not found with id: " + request.getBankId()));
            branch.setBank(bank);
        }

        Branch savedBranch = branchRepo.save(branch);
        return branchMapper.toResponse(savedBranch);
    }

    @Override
    public BranchRes update(UUID id, BranchReq request) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));

        branch.setName(request.getName());
        branch.setAddress(request.getAddress());

        if (request.getBankId() != null) {
            Bank bank = bankRepo.findById(request.getBankId())
                    .orElseThrow(() -> new RuntimeException("Bank not found with id: " + request.getBankId()));
            branch.setBank(bank);
        }

        Branch updatedBranch = branchRepo.save(branch);
        return branchMapper.toResponse(updatedBranch);
    }

    @Override
    public void delete(UUID id) {
        branchRepo.deleteById(id);
    }

    @Override
    public Optional<BranchRes> findByName(String name) {
        Branch branch = branchRepo.findByName(name);
        return branch != null ? Optional.of(branchMapper.toResponse(branch)) : Optional.empty();
    }

    @Override
    public List<BranchRes> findByBankId(UUID bankId) {
        return branchRepo.findByBankId(bankId).stream()
                .map(branchMapper::toResponse)
                .collect(Collectors.toList());
    }
}
