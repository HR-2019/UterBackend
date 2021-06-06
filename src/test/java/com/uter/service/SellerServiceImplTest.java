package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Rol;
import com.uter.entities.Seller;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.ISellerRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.ISellerServiceImpl;
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
public class SellerServiceImplTest {
    @Mock
    private ISellerRepository sellerRepository;

    @InjectMocks
    private ISellerServiceImpl sellerService;
    @Test
    public void saveTest(){
        Seller seller = new Seller(1L,"Enrique","233","paco","e@gmail.com","213456789","m");
        given(sellerRepository.save(seller)).willReturn(seller);
        Seller savedSeller = null;
        try{
            savedSeller = sellerService.save(seller);
        }catch (Exception e){

        }
        assertThat(savedSeller);
        verify(sellerRepository).save(any(Seller.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Seller> list = new ArrayList<>();
        list.add(new Seller(1L,"Enrique","233","paco","e@gmail.com","213456789","m"));
        list.add(new Seller(2L,"Alejandro","233","ale","a@gmail.com","213456789","m"));
        list.add(new Seller(3L,"Luis","233","lucho","b@gmail.com","213456789","m"));
        given(sellerRepository.findAll()).willReturn(list);
        List<Seller> expected = sellerService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        sellerService.delete(id);
        verify(sellerRepository,times(1)).deleteById(id);
    }
}
