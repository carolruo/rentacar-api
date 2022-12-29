package com.carol.rentacar.controllers;

import com.carol.rentacar.dtos.RentOrderRequest;
import com.carol.rentacar.models.RentOrder;
import com.carol.rentacar.services.RentOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class RentOrderController {

    private final RentOrderService rentService;

    public RentOrderController(RentOrderService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<RentOrder>> findAll() {
        List<RentOrder> all = rentService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RentOrder> rentById(@PathVariable("id") Long id) {
        RentOrder rentOrder = rentService.findById(id);
        return ResponseEntity.ok().body(rentOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> insert(@RequestBody @Valid RentOrderRequest rentRequest) {
        rentService.save(rentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RentOrder> update(@PathVariable("id") Long id, @RequestBody @Valid RentOrderRequest updatedRent) {
        RentOrder rentOrder = rentService.update(updatedRent, id);
        return ResponseEntity.ok().body(rentOrder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        rentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
