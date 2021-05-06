package com.uter.repository;

import com.uter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByUsername(String username);
    public List<Customer> findByName(String name);
    public List<Customer> findByPhone(String phone);
    public List<Customer> findByEmail(String email);

}
