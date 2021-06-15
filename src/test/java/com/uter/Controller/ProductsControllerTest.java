package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.ProductsController;
import com.uter.entities.*;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IProductsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductsController.class)
@ActiveProfiles("tests")
public class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IProductsServiceImpl productsService;

    private List<Products> productsList;
    private Seller seller;
    private Category category;
    @BeforeEach
    void Setup(){

        productsList = new ArrayList<>();
        productsList.add(new Products(1L,"mouse","aaaa",30.5f,seller,category));
        productsList.add(new Products(2L,"asdsads","bbbb",30.5f,seller,category));
        productsList.add(new Products(3L,"monitor","cccc",30.5f,seller,category));

    }
    @Test
    void findAllProducts() throws Exception{
        given(productsService.getAll()).willReturn(productsList);
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());

    }
    @Test
    void findProductsById() throws Exception{
        Long ProductsId = 1L;
        Products products = new Products(1L,"mouse","aaaa",30.5f,seller,category);
        given(productsService.getById(ProductsId)).willReturn(Optional.of(products));

        mockMvc.perform(get("/api/products/{id}",products.getId()))
                .andExpect(status().isOk());
    }


}
