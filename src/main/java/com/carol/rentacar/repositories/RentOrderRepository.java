package com.carol.rentacar.repositories;

import com.carol.rentacar.models.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentOrderRepository extends JpaRepository<RentOrder, Long> {
}
