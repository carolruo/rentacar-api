package com.carol.rentacar.repositories;

import com.carol.rentacar.models.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder, Long> {
}
