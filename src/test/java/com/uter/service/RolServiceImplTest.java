package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IRolRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IRolServiceImpl;
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
public class RolServiceImplTest {
    @Mock
    private IRolRepository rolRepository;

    @InjectMocks
    private IRolServiceImpl rolService;
    private List<Customer> customers;
    @Test
    public void saveTest(){
        Rol rol = new Rol(1L,"novato");
        given(rolRepository.save(rol)).willReturn(rol);
        Rol savedRol = null;
        try{
            savedRol = rolService.save(rol);
        }catch (Exception e){

        }
        assertThat(savedRol);
        verify(rolRepository).save(any(Rol.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Rol> list = new ArrayList<>();
        list.add(new Rol(1L,"novato"));
        list.add(new Rol(2L,"intermedio"));
        list.add(new Rol(3L,"avanzado"));
        given(rolRepository.findAll()).willReturn(list);
        List<Rol> expected = rolService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        rolService.delete(id);
        verify(rolRepository,times(1)).deleteById(id);
    }
}
