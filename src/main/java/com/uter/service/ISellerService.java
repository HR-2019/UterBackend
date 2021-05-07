package com.uter.service;

import com.uter.entities.Seller;

import java.util.List;

public interface ISellerService extends CrudService<Seller>{

    public Seller findByUsername(String username) throws Exception;
    public List<Seller> findByName(String name) throws Exception;
    public List<Seller> findByPhone(String phone) throws Exception;
    public List<Seller> findByEmail(String email) throws Exception;

}
