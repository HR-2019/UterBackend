package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.RolController;
import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.repository.ICustomerRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IRolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RolController.class)
@ActiveProfiles("tests")
public class RolControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IRolServiceImpl rolService;

    private List<Rol> rolList;
    private List<Customer> customers;
    @BeforeEach
    void Setup(){

        rolList = new ArrayList<>();
        rolList.add(new Rol(1L,"novato"));
        rolList.add(new Rol(2L,"medio"));
        rolList.add(new Rol(3L,"experto"));

    }
    @Test
    void findAllRol() throws Exception{
        given(rolService.getAll()).willReturn(rolList);
        mockMvc.perform(get("/api/rol")).andExpect(status().isOk());

    }
    @Test
    void findRolById() throws Exception{
        Long RolId = 1L;
        Rol rol = new Rol(1L,"novato");
        given(rolService.getById(RolId)).willReturn(Optional.of(rol));

        mockMvc.perform(get("/api/rol/{id}",rol.getId()))
                .andExpect(status().isOk());
    }


}
