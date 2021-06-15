
package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Orders;
import com.uter.entities.Rol;
import com.uter.entities.Seller;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IOrdersRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IOrdersServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceImplTest {
    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private IOrdersServiceImpl ordersService;
    private Seller seller;
    private Customer customer;
    @Test
    public void saveTest(){
        Orders orders = new Orders(1L,seller,customer,ParseDate("2021-06-15T04:31:10.855Z"));
        given(ordersRepository.save(orders)).willReturn(orders);
        Orders savedOrders = null;
        try{
            savedOrders = ordersService.save(orders);
        }catch (Exception e){

        }
        assertThat(savedOrders);
        verify(ordersRepository).save(any(Orders.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Orders> list = new ArrayList<>();
        list.add(new Orders(1L,seller,customer,ParseDate("2021-06-15T04:31:10.855Z")));
        list.add(new Orders(2L,seller,customer,ParseDate("2021-06-15T04:31:10.855Z")));
        list.add(new Orders(3L,seller,customer,ParseDate("2021-06-15T04:31:10.855Z")));
        given(ordersRepository.findAll()).willReturn(list);
        List<Orders> expected = ordersService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        ordersService.delete(id);
        verify(ordersRepository,times(1)).deleteById(id);
    }
    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception ex){
        }
        return result;
    }
}
