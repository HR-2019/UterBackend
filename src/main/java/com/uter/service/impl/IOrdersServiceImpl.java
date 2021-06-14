package com.uter.service.impl;

import com.uter.entities.Orders;
import com.uter.repository.IOrdersRepository;
import com.uter.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersRepository orderRepository;

    @Override
    @Transactional
    public Orders save(Orders orders) throws Exception {
        return orderRepository.save(orders);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> getById(Long id) throws Exception {
        return orderRepository.findById(id);
    }

    @Override
    public List<Orders> findBetweenDates(Date fromDate, Date toDate) throws Exception {
        return orderRepository.findBetweenDates(fromDate, toDate);
    }
}
