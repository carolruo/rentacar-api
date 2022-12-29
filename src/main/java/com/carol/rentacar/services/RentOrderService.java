package com.carol.rentacar.services;

import com.carol.rentacar.dtos.RentOrderRequest;
import com.carol.rentacar.exceptions.InvalidRentStatusException;
import com.carol.rentacar.exceptions.ObjectNotFoundException;
import com.carol.rentacar.models.Car;
import com.carol.rentacar.models.Customer;
import com.carol.rentacar.models.RentOrder;
import com.carol.rentacar.models.enums.RentStatus;
import com.carol.rentacar.repositories.RentOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RentOrderService {

    private final RentOrderRepository rentOrderRepository;
    private final CustomerService customerService;
    private final CarService carService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

        RentOrder rentOrder = new RentOrder(customer, car, rentRequest.getStatus());
        setRentTime(rentOrder);

        rentOrderRepository.save(rentOrder);
    }

    private void setRentTime(RentOrder rentOrder) {
        if (rentOrder.getStatus() == null) {
            rentOrder.setStatus(RentStatus.ACTIVE);
            rentOrder.setStartTime(LocalDateTime.now().format(formatter));
        } else if (rentOrder.getStatus() == RentStatus.CLOSED || rentOrder.getStatus() == RentStatus.CANCELED) {
            rentOrder.setFinishTime(LocalDateTime.now().format(formatter));
        }
    }

    public RentOrder update(RentOrderRequest updatedRent, Long id) {
        RentOrder rentOrder = findById(id);

        rentOrder.setCar(carService.findById(updatedRent.getCarId()));
        rentOrder.setCustomer(customerService.findById(updatedRent.getCustomerId()));

        validateRentStatus(updatedRent, rentOrder);

        if (updatedRent.getStatus() != null) {
            rentOrder.setStatus(updatedRent.getStatus());
        }

        setRentTime(rentOrder);

        rentOrderRepository.save(rentOrder);

        return rentOrder;
    }

    private void validateRentStatus(RentOrderRequest updatedRent, RentOrder rentOrder) {
        if (updatedRent.getStatus() == RentStatus.ACTIVE && (rentOrder.getStatus() == RentStatus.CLOSED || rentOrder.getStatus() == RentStatus.CANCELED)){
            throw new InvalidRentStatusException("Atualização de status de locação inválido. Status da locação: " + rentOrder.getStatus());
        }
    }

    public void delete(Long id) {
        RentOrder rentOrder = findById(id);
        rentOrderRepository.delete(rentOrder);
    }
}
