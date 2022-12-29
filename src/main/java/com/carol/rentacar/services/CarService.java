package com.carol.rentacar.services;

import com.carol.rentacar.exceptions.DuplicateObjectException;
import com.carol.rentacar.exceptions.ObjectNotFoundException;
import com.carol.rentacar.models.Car;
import com.carol.rentacar.models.enums.CarStatus;
import com.carol.rentacar.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado. Id: " + id));
    }

    public void save(Car car) {
        if (car.getStatus() == null) {
            car.setStatus(CarStatus.AVAILABLE);
        }

        if (carRepository.findByPlate(car.getPlate()).isPresent()) {
            throw new DuplicateObjectException("Carro com essa placa já cadastrado. Placa: " + car.getPlate());
        }
        carRepository.save(car);
    }

    public Car update(Car updatedCar, Long id) {
        Car car = findById(id);

        car.setModel(updatedCar.getModel());
        car.setColor(updatedCar.getColor());
        car.setYear(updatedCar.getYear());
        car.setPlate(updatedCar.getPlate());

        carRepository.save(car);

        return car;
    }

    public void delete(Long id) {
        Car car = findById(id);
        carRepository.delete(car);
    }

}
