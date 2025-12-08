package com.gharib.banking.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gharib.banking.Abstract.CustomerService;
import com.gharib.banking.models.Customer;
import com.gharib.banking.models.Dto.CustomerReq;
import com.gharib.banking.models.Dto.CustomerRes;
import com.gharib.banking.repos.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public Optional<CustomerRes> findById(UUID id) {
        return customerRepo.findById(id).map(this::convertToCustomerRes);
    }

    @Override
    public List<CustomerRes> findAll() {
        return customerRepo.findAll().stream()
                .map(this::convertToCustomerRes)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerRes create(CustomerReq request) {
        Customer customer = convertToCustomer(request);
        Customer savedCustomer = customerRepo.save(customer);
        return convertToCustomerRes(savedCustomer);
    }

    @Override
    public CustomerRes update(UUID id, CustomerReq request) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        updateCustomerFromRequest(customer, request);

        Customer updatedCustomer = customerRepo.save(customer);
        return convertToCustomerRes(updatedCustomer);
    }

    @Override
    public void delete(UUID id) {
        customerRepo.deleteById(id);
    }

    @Override
    public Optional<CustomerRes> findByName(String name) {
        Customer customer = customerRepo.findByName(name);
        return customer != null ? Optional.of(convertToCustomerRes(customer)) : Optional.empty();
    }

    @Override
    public Optional<CustomerRes> findByPhone(Integer phone) {
        Customer customer = customerRepo.findByPhone(phone);
        return customer != null ? Optional.of(convertToCustomerRes(customer)) : Optional.empty();
    }

    // Helper methods for conversion
    private CustomerRes convertToCustomerRes(Customer customer) {
        CustomerRes response = new CustomerRes();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setAddress(customer.getAddress());
        response.setPhone(customer.getPhone());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }

    private Customer convertToCustomer(CustomerReq request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        return customer;
    }

    private void updateCustomerFromRequest(Customer customer, CustomerReq request) {
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
    }
}
