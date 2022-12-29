package com.carol.rentacar.services;

import com.carol.rentacar.exceptions.ObjectNotFoundException;
import com.carol.rentacar.models.Customer;
import com.carol.rentacar.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id: " + id));
    }

    public void save(Customer customer) {
        customer.getAddress().setCustomer(customer);
        customerRepository.save(customer);
    }

    public Customer update(Customer updatedCustomer, Long id) {
        Customer customer = findById(id);

        customer.setName(updatedCustomer.getName());
        customer.setCpf(updatedCustomer.getCpf());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customer.setAddress(updatedCustomer.getAddress());

        customerRepository.save(customer);

        return customer;
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}
