package com.uter.Controller;

import com.uter.controller.CustomerController;
import com.uter.controller.ReviewsController;
import com.uter.entities.Customer;
import com.uter.entities.Reviews;
import com.uter.entities.Rol;
import com.uter.entities.Seller;
import com.uter.service.IReviewsService;
import com.uter.service.impl.ICustomerServiceImpl;
import com.uter.service.impl.IReviewsServiceImpl;
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

@WebMvcTest(controllers = ReviewsController.class)
@ActiveProfiles("tests")
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IReviewsServiceImpl reviewsService;

    private List<Reviews> reviewsList;
    private Seller seller;
    private Customer customer;
    @BeforeEach
    void Setup(){

        reviewsList = new ArrayList<>();
        reviewsList.add(new Reviews(1L,"excelente",5,seller,customer));
        reviewsList.add(new Reviews(2L,"bueno",3,seller,customer));
        reviewsList.add(new Reviews(3L,"malo",1,seller,customer));

    }
    @Test
    void findAllReviews() throws Exception{
        given(reviewsService.getAll()).willReturn(reviewsList);
        mockMvc.perform(get("/api/reviews")).andExpect(status().isOk());

    }
    @Test
    void findReviewsById() throws Exception{
        Long ReviewsId = 1L;
        Reviews review = new Reviews(1L,"excelente",5,seller,customer);
        given(reviewsService.getById(ReviewsId)).willReturn(Optional.of(review));

        mockMvc.perform(get("/api/reviews/{id}",review.getId()))
                .andExpect(status().isOk());
    }


}
