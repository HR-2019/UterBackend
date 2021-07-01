package com.uter.service.impl;

import com.uter.entities.OrderDetails;
import com.uter.repository.IOrderDetailsRepository;
import com.uter.service.IOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IOrderDetailsServiceImpl implements IOrderDetailsService {

    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Override
    @Transactional
    public OrderDetails save(OrderDetails orderDetails) throws Exception {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> getAll() throws Exception {
        return orderDetailsRepository.findAll();
    }

    @Override
    public Optional<OrderDetails> getById(Long id) throws Exception {
        return orderDetailsRepository.findById(id);
    }

    @Override
    public List<OrderDetails> findDiscount(int discount) throws Exception {
        return orderDetailsRepository.findDiscount(discount);
    }
}
