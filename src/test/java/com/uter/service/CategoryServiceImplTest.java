package com.uter.service;

import com.uter.entities.Category;
import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.repository.ICategoryRepository;
import com.uter.repository.ICustomerRepository;
import com.uter.service.impl.ICategoryServiceImpl;
import com.uter.service.impl.ICustomerServiceImpl;
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
public class CategoryServiceImplTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private ICategoryServiceImpl categoryService;
    @Test
    public void saveTest(){
        Category category = new Category(1L,"Computadora","Computadora diseñada para una sola persona");
        given(categoryRepository.save(category)).willReturn(category);
        Category savedCategory = null;
        try{
            savedCategory = categoryService.save(category);
        }catch (Exception e){

        }
        assertThat(savedCategory);
        verify(categoryRepository).save(any(Category.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Category> list = new ArrayList<>();
        list.add(new Category(1L,"Computadora","Computadora diseñada para una sola persona"));
        list.add(new Category(1L,"Mouse","Es un dispositivo apuntador utilizado para facilitar el manejo de un entorno gráfico en una computadora."));
        list.add(new Category(1L,"Teclado","Es un dispositivo o periférico de entrada, en parte inspirado en el teclado de las máquinas de escribir, que utiliza un sistema de botones o teclas"));
        given(categoryRepository.findAll()).willReturn(list);
        List<Category> expected = categoryService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        categoryService.delete(id);
        verify(categoryRepository,times(1)).deleteById(id);
    }
}
