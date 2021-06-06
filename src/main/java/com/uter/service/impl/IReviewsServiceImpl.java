package com.uter.service.impl;

import com.uter.entities.Reviews;
import com.uter.repository.ICustomerRepository;
import com.uter.repository.IReviewsRepository;
import com.uter.service.ICustomerService;
import com.uter.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IReviewsServiceImpl implements IReviewsService {

    @Autowired
    private IReviewsRepository reviewsRepository;

    @Override
    @Transactional
    public Reviews save(Reviews reviews) throws Exception {
        return reviewsRepository.save(reviews) ;
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        reviewsRepository.deleteById(id);
    }

    @Override
    public List<Reviews> getAll() throws Exception {
        return reviewsRepository.findAll();
    }

    @Override
    public Optional<Reviews> getById(Long id) throws Exception {
        return reviewsRepository.findById(id);
    }

    @Override
    public List<Reviews> findDescription(String description) throws Exception {
        return reviewsRepository.findDescription(description);
    }

    @Override
    public List<Reviews> findStars(int stars) throws Exception {
        return reviewsRepository.findStars(stars);
    }
}
