package com.uter.repository;

import com.uter.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    public List<Rol> findByName(String name);

}