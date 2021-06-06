package com.uter.service.impl;

import com.uter.entities.Products;
import com.uter.repository.IProductsRepository;
import com.uter.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IProductsServiceImpl implements IProductsService {

    @Autowired
    private IProductsRepository productsRepository;

    @Override
    @Transactional
    public Products save(Products products) throws Exception {
        return productsRepository.save(products);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        productsRepository.deleteById(id);
    }

    @Override
    public List<Products> getAll() throws Exception {
        return productsRepository.findAll();
    }

    @Override
    public Optional<Products> getById(Long id) throws Exception {
        return productsRepository.findById(id);
    }

    @Override
    public List<Products> findByName(String name) throws Exception {
        return productsRepository.findByName(name);
    }
}
