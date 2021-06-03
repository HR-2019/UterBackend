package com.uter.service;

import com.uter.entities.Reviews;
import com.uter.entities.Services;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServicesService extends CrudService<Services> {
    public List<Services> findByName(String name) throws Exception;
    public List<Reviews> findDescription(@Param("descrip") String description) throws Exception;
}
