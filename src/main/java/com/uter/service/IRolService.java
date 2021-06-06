package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Rol;

import java.util.List;

public interface IRolService extends CrudService<Rol>{
    public List<Rol> findByName(String name) throws Exception;
}