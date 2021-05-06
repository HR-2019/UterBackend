package com.uter.service.impl;

import com.uter.entities.Customer;
import com.uter.repository.ICustomerRepository;
import com.uter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//transactional solo se usa cuando no es una consulta = update, add y delete
@Service
@Transactional(readOnly = true)
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByUsername(String username) throws Exception {
        return customerRepository.findByUsername(username);
    }

    @Override
    public List<Customer> findByName(String name) throws Exception {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> findByPhone(String phone) throws Exception {
        return customerRepository.findByPhone(phone);
    }

    @Override
    public List<Customer> findByEmail(String email) throws Exception {
        return customerRepository.findByEmail(email);
    }
}
