package com.uter.service.impl;

import com.uter.entities.Rol;
import com.uter.repository.IRolRepository;
import com.uter.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IRolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    @Transactional
    public Rol save(Rol rol) throws Exception {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        rolRepository.deleteById(id);
    }

    @Override
    public List<Rol> getAll() throws Exception {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> getById(Long id) throws Exception {
        return rolRepository.findById(id);
    }


    @Override
    public List<Rol> findByName(String name) throws Exception {
        return rolRepository.findByName(name);
    }
}