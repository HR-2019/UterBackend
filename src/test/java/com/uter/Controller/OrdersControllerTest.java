package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.OrdersController;
import com.uter.entities.Customer;
import com.uter.entities.Orders;
import com.uter.entities.Seller;
import com.uter.service.IOrdersService;
import com.uter.service.impl.IOrdersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrdersController.class)
@ActiveProfiles("tests")
public class OrdersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IOrdersServiceImpl ordersService;

    private List<Orders> ordersList;
    private Seller seller;
    private Customer customer;

    @BeforeEach
    void Setup(){

        ordersList = new ArrayList<>();
        ordersList.add(new Orders(1L, seller, customer, ParseDate("2021-06-15T04:31:10.855Z")));
        ordersList.add(new Orders(2L, seller, customer, ParseDate("2021-06-15T04:31:10.855Z")));
        ordersList.add(new Orders(3L, seller, customer, ParseDate("2021-06-15T04:31:10.855Z")));

    }
    @Test
    void findAllOrders() throws Exception{
        given(ordersService.getAll()).willReturn(ordersList);
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());

    }
    @Test
    void findOrdersById() throws Exception{
        Long OrdersId = 1L;
        Orders orders = new Orders(1L, seller, customer, ParseDate("2021-06-15T04:31:10.855Z"));
        given(ordersService.getById(OrdersId)).willReturn(Optional.of(orders));

        mockMvc.perform(get("/api/orders/{id}",orders.getId()))
                .andExpect(status().isOk());
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