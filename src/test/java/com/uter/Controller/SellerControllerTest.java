package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.SellerController;
import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.entities.Seller;
import com.uter.service.ISellerService;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.ISellerServiceImpl;
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

@WebMvcTest(controllers = SellerController.class)
@ActiveProfiles("tests")
public class SellerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ISellerServiceImpl sellerService;

    private List<Seller> sellerList;
    @BeforeEach
    void Setup(){

        sellerList = new ArrayList<>();
        sellerList.add(new Seller(1L,"Enrique","233","paco","e@gmail.com","213456789","m"));
        sellerList.add(new Seller(2L,"Alejandro","233","ale","a@gmail.com","213456789","m"));
        sellerList.add(new Seller(3L,"Luis","233","lucho","b@gmail.com","213456789","m"));

    }
    @Test
    void findAllSeller() throws Exception{
        given(sellerService.getAll()).willReturn(sellerList);
        mockMvc.perform(get("/api/sellers")).andExpect(status().isOk());

    }
    @Test
    void findSellerById() throws Exception{
        Long SellerId = 1L;
        Seller seller = new Seller(1L,"Enrique","233","paco","e@gmail.com","213456789","m");
        given(sellerService.getById(SellerId)).willReturn(Optional.of(seller));

        mockMvc.perform(get("/api/sellers/{id}",seller.getId()))
                .andExpect(status().isOk());
    }


}
