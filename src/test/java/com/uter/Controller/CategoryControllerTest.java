package com.uter.Controller;

import com.uter.controller.CategoryController;
import com.uter.entities.Category;
import com.uter.entities.Customer;
import com.uter.service.impl.ICategoryServiceImpl;
import com.uter.service.impl.ICustomerServiceImpl;
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

@WebMvcTest(controllers = CategoryController.class)
@ActiveProfiles("tests")
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICategoryServiceImpl categoryService;

    private List<Category> categoryList;

    @BeforeEach
    void Setup(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1L,"Computadora","Computadora diseñada para una sola persona"));
        categoryList.add(new Category(1L,"Mouse","Es un dispositivo apuntador utilizado para facilitar el manejo de un entorno gráfico en una computadora."));
        categoryList.add(new Category(1L,"Teclado","Es un dispositivo o periférico de entrada, en parte inspirado en el teclado de las máquinas de escribir, que utiliza un sistema de botones o teclas"));
    }
    @Test
    void findAllCustomer() throws Exception{
        given(categoryService.getAll()).willReturn(categoryList);
        mockMvc.perform(get("/api/categories")).andExpect(status().isOk());

    }
    @Test
    void findCategoryById() throws Exception{
        Long CategoryId = 1L;
        Category category = new Category(1L,"Computadora","Computadora diseñada para una sola persona");
        given(categoryService.getById(CategoryId)).willReturn(Optional.of(category));

        mockMvc.perform(get("/api/categories/{id}",category.getId()))
                .andExpect(status().isOk());
    }
}
