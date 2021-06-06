package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Products;

import java.util.List;

public interface IProductsService extends CrudService<Products> {

    public List<Products> findByName(String name) throws Exception;

}
