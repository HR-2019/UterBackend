package com.uter.service.impl;

import com.uter.entities.Category;
import com.uter.repository.ICategoryRepository;
import com.uter.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    @Transactional
    public Category save(Category category) throws Exception {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAll() throws Exception {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) throws Exception {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findByName(String name) throws Exception {
        return categoryRepository.findByName(name);
    }
}
