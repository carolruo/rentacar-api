package com.carol.rentacar.services;

import com.carol.rentacar.exceptions.DuplicateObjectException;
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
        validateEmail(customer);
        validatePhoneNumber(customer);
        validateCpf(customer);
        customer.getAddress().setCustomer(customer);
        customerRepository.save(customer);
    }

    private void validateCpf(Customer customer) {
        if (customerRepository.findByCpf(customer.getCpf()).isPresent()) {
            throw new DuplicateObjectException("Cliente com esse CPF já cadastrado");
        }
    }

    private void validatePhoneNumber(Customer customer) {
        if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
            throw new DuplicateObjectException("Cliente com esse telefone já cadastrado");
        }
    }

    private void validateEmail(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new DuplicateObjectException("Cliente com esse e-mail já cadastrado");
        }
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
