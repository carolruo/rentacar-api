package com.carol.rentacar.repositories;

import com.carol.rentacar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
