package com.uter.service;

import com.uter.entities.*;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IProductsRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IProductsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private IProductsRepository productsRepository;

    @InjectMocks
    private IProductsServiceImpl productsService;
    private Seller seller;
    private Category category;
    @Test
    public void saveTest(){
        Products products = new Products(1L,"mouse","aaaa",30.5f,seller,category);
        given(productsRepository.save(products)).willReturn(products);
        Products savedProducts = null;
        try{
            savedProducts = productsService.save(products);
        }catch (Exception e){

        }
        assertThat(savedProducts);
        verify(productsRepository).save(any(Products.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Products> list = new ArrayList<>();
        list.add(new Products(1L,"mouse","aaaa",30.5f,seller,category));
        list.add(new Products(2L,"asdsads","bbbb",30.5f,seller,category));
        list.add(new Products(3L,"monitor","cccc",30.5f,seller,category));

        given(productsRepository.findAll()).willReturn(list);
        List<Products> expected = productsService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        productsService.delete(id);
        verify(productsRepository,times(1)).deleteById(id);
    }
}
