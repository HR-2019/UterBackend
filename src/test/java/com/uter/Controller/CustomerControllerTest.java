package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.service.impl.ICustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("tests")
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICustomerServiceImpl customerService;

    private List<Customer> customerList;
    private Rol rol;
    @BeforeEach
    void Setup(){

        customerList = new ArrayList<>();
        customerList.add(new Customer(1L,"Enrique","233","paco","e@gmail.com","213456789","m",rol));
        customerList.add(new Customer(2L,"Alejandro","233","ale","a@gmail.com","213456789","m",rol));
        customerList.add(new Customer(3L,"Luis","233","lucho","b@gmail.com","213456789","m",rol));

    }
    @Test
    void findAllCustomer() throws Exception{
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
        
    }
    @Test
    void findCustomerById() throws Exception{
        Long CustomerId = 1L;
        Customer customer = new Customer(1L,"Enrique","233","paco","e@gmail.com","213456789","m",rol);
        given(customerService.getById(CustomerId)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/{id}",customer.getId()))
                .andExpect(status().isOk());
    }


}
