package com.uter.service.impl;

import com.uter.entities.Seller;
import com.uter.repository.ISellerRepository;
import com.uter.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ISellerServiceImpl implements ISellerService {

    @Autowired
    private ISellerRepository sellerRepository;


    @Override
    @Transactional
    public Seller save(Seller seller) throws Exception {
        return sellerRepository.save(seller);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        sellerRepository.deleteById(id);
    }

    @Override
    public List<Seller> getAll() throws Exception {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getById(Long id) throws Exception {
        return sellerRepository.findById(id);
    }

    @Override
    public Seller findByUsername(String username) throws Exception {
        return sellerRepository.findByUsername(username);
    }

    @Override
    public List<Seller> findByName(String name) throws Exception {
        return sellerRepository.findByName(name);
    }

    @Override
    public List<Seller> findByPhone(String phone) throws Exception {
        return sellerRepository.findByPhone(phone);
    }

    @Override
    public List<Seller> findByEmail(String email) throws Exception {
        return sellerRepository.findByEmail(email);
    }
}
