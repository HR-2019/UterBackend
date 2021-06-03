package com.uter.service.impl;

import com.uter.entities.Reviews;
import com.uter.entities.Services;
import com.uter.repository.IReviewsRepository;
import com.uter.repository.IServicesRepository;
import com.uter.service.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IServicesServiceImpl implements IServicesService {

    @Autowired
    private IServicesRepository servicesRepository;

    @Override
    public Services save(Services services) throws Exception {
        return servicesRepository.save(services);
    }

    @Override
    public void delete(Long id) throws Exception {
        servicesRepository.deleteById(id);
    }

    @Override
    public List<Services> getAll() throws Exception {
        return servicesRepository.findAll();
    }

    @Override
    public Optional<Services> getById(Long id) throws Exception {
        return servicesRepository.findById(id);
    }

    @Override
    public List<Services> findByName(String name) throws Exception {
        return servicesRepository.findByName(name);
    }

    @Override
    public List<Reviews> findDescription(String description) throws Exception {
        return servicesRepository.findDescription(description);
    }
}
