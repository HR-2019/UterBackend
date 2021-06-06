package com.uter.repository;

import com.uter.entities.Customer;
import com.uter.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long> {

    public List<Products> findByName(String name);

}
