package com.carol.rentacar.controllers;

import com.carol.rentacar.models.Customer;
import com.carol.rentacar.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Customer>> findAll() {
        List<Customer> all = customerService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Customer> clientById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> insert(@RequestBody @Valid Customer customer) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody @Valid Customer updatedCustomer) {
        Customer customer = customerService.update(updatedCustomer, id);
        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
