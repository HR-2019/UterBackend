package com.uter.repository;

import com.uter.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISellerRepository extends JpaRepository<Seller, Long> {

    public Seller findByUsername(String username);
    public List<Seller> findByName(String name);
    public List<Seller> findByPhone(String phone);
    public List<Seller> findByEmail(String email);

}
