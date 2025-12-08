package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.CustomerService;
import com.gharib.banking.mappers.CustomerMapper;
import com.gharib.banking.models.Customer;
import com.gharib.banking.models.Dto.CustomerReq;
import com.gharib.banking.models.Dto.CustomerRes;
import com.gharib.banking.repos.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Optional<CustomerRes> findById(UUID id) {
        return customerRepo.findById(id).map(customerMapper::toResponse);
    }

    @Override
    public List<CustomerRes> findAll() {
        return customerRepo.findAll().stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerRes create(CustomerReq request) {
        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepo.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerRes update(UUID id, CustomerReq request) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());

        Customer updatedCustomer = customerRepo.save(customer);
        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    public void delete(UUID id) {
        customerRepo.deleteById(id);
    }

    @Override
    public Optional<CustomerRes> findByName(String name) {
        Customer customer = customerRepo.findByName(name);
        return customer != null ? Optional.of(customerMapper.toResponse(customer)) : Optional.empty();
    }

    @Override
    public Optional<CustomerRes> findByPhone(Integer phone) {
        Customer customer = customerRepo.findByPhone(phone);
        return customer != null ? Optional.of(customerMapper.toResponse(customer)) : Optional.empty();
    }
}
