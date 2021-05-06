package com.uter.service;

import com.uter.entities.Customer;

import java.util.List;


public interface ICustomerService extends CrudService<Customer>{
    public Customer findByUsername(String username) throws Exception;
    public List<Customer> findByName(String name) throws Exception;
    public List<Customer> findByPhone(String phone) throws Exception;
    public List<Customer> findByEmail(String email) throws Exception;
}
