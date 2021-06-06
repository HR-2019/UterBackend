package com.uter.service;

import com.uter.entities.Customer;
import com.uter.entities.Reviews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewsService extends CrudService<Reviews>{
    @Query("Select b from Reviews b where b.description =:descrip")
    public List<Reviews> findDescription(@Param("descrip") String description) throws Exception;
    @Query("Select c from Reviews c where c.stars =:star")
    public List<Reviews> findStars(@Param("star") int stars) throws Exception;
}