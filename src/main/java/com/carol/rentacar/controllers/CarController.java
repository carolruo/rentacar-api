package com.carol.rentacar.controllers;

import com.carol.rentacar.models.Car;
import com.carol.rentacar.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarController {

    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Car>> findAll() {
        List<Car> all = carService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Car> carById(@PathVariable("id") Long id) {
        Car car = carService.findById(id);
        return ResponseEntity.ok().body(car);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> insert(@RequestBody @Valid Car car) {
        carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Car> update(@PathVariable("id") Long id, @RequestBody @Valid Car updatedCar) {
        Car car = carService.update(updatedCar, id);
        return ResponseEntity.ok().body(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
