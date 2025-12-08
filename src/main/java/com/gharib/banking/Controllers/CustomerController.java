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

import com.gharib.banking.models.Dto.CustomerReq;
import com.gharib.banking.models.Dto.CustomerRes;

import jakarta.validation.Valid;
import com.gharib.banking.services.CustomerServiceImp;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImp customerService;

    @GetMapping
    public List<CustomerRes> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/search")
    public List<CustomerRes> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer phone) {

        if (name != null) {
            return customerService.findByName(name)
                    .map(List::of)
                    .orElse(List.of());
        }

        if (phone != null) {
            return customerService.findByPhone(phone)
                    .map(List::of)
                    .orElse(List.of());
        }

        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CustomerRes> getCustomerById(@PathVariable UUID id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerRes createCustomer(@Valid @RequestBody CustomerReq customerReq) {
        return customerService.create(customerReq);
    }

    @PutMapping("/{id}")
    public CustomerRes updateCustomer(@PathVariable UUID id, @Valid @RequestBody CustomerReq customerReq) {
        return customerService.update(id, customerReq);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.delete(id);
    }

}
