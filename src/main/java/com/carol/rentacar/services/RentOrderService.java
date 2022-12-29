package com.carol.rentacar.services;

import com.carol.rentacar.exceptions.ObjectNotFoundException;
import com.carol.rentacar.models.RentOrder;
import com.carol.rentacar.repositories.RentOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentOrderService {

    private final RentOrderRepository rentOrderRepository;

    public RentOrderService(RentOrderRepository rentOrderRepository) {
        this.rentOrderRepository = rentOrderRepository;
    }

    public List<RentOrder> findAll() {
        return rentOrderRepository.findAll();
    }

    public RentOrder findById(Long id) {
        return rentOrderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ordem de locação não encontrada. Id: " + id));
    }

    public void save(RentOrder rentOrder) {
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
