package com.uter.repository;

import com.uter.entities.Reviews;
import com.uter.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServicesRepository extends JpaRepository<Services, Long> {
    public List<Services> findByName(String name);
    @Query("Select b from Reviews b where b.description =:descrip")
    public List<Reviews> findDescription(@Param("descrip") String description);
}
