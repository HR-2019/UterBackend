package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.repository.ICustomerRepository;
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
public class CustomerServiceImplTest {
    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private ICustomerServiceImpl customerService;
    private Rol rol;
    @Test
    public void saveTest(){
        Customer customer = new Customer(1L,"Enrique","233","paco","e@gmail.com","213456789","m",rol);
        given(customerRepository.save(customer)).willReturn(customer);
        Customer savedCustomer = null;
        try{
            savedCustomer = customerService.save(customer);
        }catch (Exception e){

        }
        assertThat(savedCustomer);
        verify(customerRepository).save(any(Customer.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L,"Enrique","233","paco","e@gmail.com","213456789","m",rol));
        list.add(new Customer(2L,"Alejandro","233","ale","a@gmail.com","213456789","m",rol));
        list.add(new Customer(3L,"Luis","233","lucho","b@gmail.com","213456789","m",rol));
        given(customerRepository.findAll()).willReturn(list);
        List<Customer> expected = customerService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        customerService.delete(id);
        verify(customerRepository,times(1)).deleteById(id);
    }
}
