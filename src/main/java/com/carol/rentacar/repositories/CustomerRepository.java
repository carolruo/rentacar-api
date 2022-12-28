package com.carol.rentacar.repositories;

import com.carol.rentacar.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
