package com.uter.repository;

import com.uter.entities.Category;
import com.uter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByName(String name);

}
