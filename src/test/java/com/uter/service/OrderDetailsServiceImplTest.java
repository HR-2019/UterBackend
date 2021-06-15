package com.uter.service;

import com.uter.entities.*;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IOrderDetailsRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IOrderDetailsServiceImpl;
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
public class OrderDetailsServiceImplTest {
    @Mock
    private IOrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private IOrderDetailsServiceImpl orderDetailsService;
    private Orders orders;
    private Products products;
    @Test
    public void saveTest(){
        OrderDetails orderDetails = new OrderDetails(1L,orders,products,10.0f,50,80);
        given(orderDetailsRepository.save(orderDetails)).willReturn(orderDetails);
        OrderDetails savedOrderDetails = null;
        try{
            savedOrderDetails = orderDetailsService.save(orderDetails);
        }catch (Exception e){

        }
        assertThat(savedOrderDetails);
        verify(orderDetailsRepository).save(any(OrderDetails.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<OrderDetails> list = new ArrayList<>();
        list.add(new OrderDetails(1L,orders,products,10.0f,50,80));
        list.add(new OrderDetails(2L,orders,products,30.0f,5,30));
        list.add(new OrderDetails(3L,orders,products,20.0f,10,60));
        given(orderDetailsRepository.findAll()).willReturn(list);
        List<OrderDetails> expected = orderDetailsService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        orderDetailsService.delete(id);
        verify(orderDetailsRepository,times(1)).deleteById(id);
    }
}
