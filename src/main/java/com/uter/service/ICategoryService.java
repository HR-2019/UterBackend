package com.uter.service;

import com.uter.entities.Category;
import com.uter.entities.Customer;

import java.util.List;

public interface ICategoryService extends CrudService<Category>{

    public List<Category> findByName(String name) throws Exception;

}
