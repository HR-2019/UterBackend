package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.OrderDetailsController;
import com.uter.entities.*;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IOrderDetailsServiceImpl;
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

@WebMvcTest(controllers = OrderDetailsController.class)
@ActiveProfiles("tests")
public class OrderDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IOrderDetailsServiceImpl orderDetailsService;

    private List<OrderDetails> orderDetailsList;
    private Orders orders;
    private Products products;
    @BeforeEach
    void Setup(){

        orderDetailsList = new ArrayList<>();
        orderDetailsList.add(new OrderDetails(1L,orders,products,10.0f,50,80));
        orderDetailsList.add(new OrderDetails(2L,orders,products,30.0f,5,30));
        orderDetailsList.add(new OrderDetails(3L,orders,products,20.0f,10,60));

    }
    @Test
    void findAllOrderDetails() throws Exception{
        given(orderDetailsService.getAll()).willReturn(orderDetailsList);
        mockMvc.perform(get("/api/orderdetails")).andExpect(status().isOk());

    }
    @Test
    void findOrderDetailsById() throws Exception{
        Long OrderDetailsId = 1L;
        OrderDetails orderDetails = new OrderDetails(1L,orders,products,10.0f,50,80);
        given(orderDetailsService.getById(OrderDetailsId)).willReturn(Optional.of(orderDetails));

        mockMvc.perform(get("/api/orderdetails/{id}",orderDetails.getId()))
                .andExpect(status().isOk());
    }
}
