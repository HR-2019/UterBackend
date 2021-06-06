package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Reviews;
import com.uter.entities.Rol;
import com.uter.entities.Seller;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IReviewsRepository;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IReviewsServiceImpl;
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
public class ReviewsServiceImplTest {
    @Mock
    private IReviewsRepository reviewsRepository;

    @InjectMocks
    private IReviewsServiceImpl reviewsService;
    private Seller seller;
    private Customer customer;
    @Test
    public void saveTest(){
        Reviews reviews = new Reviews(1L,"excelente",5,seller,customer);
        given(reviewsRepository.save(reviews)).willReturn(reviews);
        Reviews savedReviews = null;
        try{
            savedReviews = reviewsService.save(reviews);
        }catch (Exception e){

        }
        assertThat(savedReviews);
        verify(reviewsRepository).save(any(Reviews.class));
    }
    @Test
    void findAllTest() throws Exception{
        List<Reviews> list = new ArrayList<>();
        list.add(new Reviews(1L,"excelente",5,seller,customer));
        list.add(new Reviews(2L,"bueno",3,seller,customer));
        list.add(new Reviews(3L,"malo",1,seller,customer));
        given(reviewsRepository.findAll()).willReturn(list);
        List<Reviews> expected = reviewsService.getAll();
        assertEquals(expected,list);
    }
    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        reviewsService.delete(id);
        verify(reviewsRepository,times(1)).deleteById(id);
    }
}
