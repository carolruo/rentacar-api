package com.carol.rentacar.services;

import com.carol.rentacar.dtos.RentOrderRequest;
import com.carol.rentacar.exceptions.ObjectNotFoundException;
import com.carol.rentacar.models.Car;
import com.carol.rentacar.models.Customer;
import com.carol.rentacar.models.RentOrder;
import com.carol.rentacar.models.enums.RentStatus;
import com.carol.rentacar.repositories.RentOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentOrderService {

    private final RentOrderRepository rentOrderRepository;
    private final CustomerService customerService;
    private final CarService carService;

    public RentOrderService(RentOrderRepository rentOrderRepository, CustomerService customerService, CarService carService) {
        this.rentOrderRepository = rentOrderRepository;
        this.customerService = customerService;
        this.carService = carService;
    }

    public List<RentOrder> findAll() {
        return rentOrderRepository.findAll();
    }

    public RentOrder findById(Long id) {
        return rentOrderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ordem de locação não encontrada. Id: " + id));
    }

    public void save(RentOrderRequest rentRequest) {
        Customer customer = customerService.findById(rentRequest.getCustomerId());
        Car car = carService.findById(rentRequest.getCarId());

        RentOrder rentOrder = new RentOrder(customer, car);
        rentOrder.setStatus(RentStatus.ACTIVE);

        rentOrderRepository.save(rentOrder);
    }

    public RentOrder update(RentOrder updatedRent, Long id) {
        RentOrder rentOrder = findById(id);

        rentOrder.setCar(updatedRent.getCar());
        rentOrder.setCustomer(updatedRent.getCustomer());

        rentOrderRepository.save(rentOrder);

        return rentOrder;
    }

    public void delete(Long id) {
        RentOrder rentOrder = findById(id);
        rentOrderRepository.delete(rentOrder);
    }
}
