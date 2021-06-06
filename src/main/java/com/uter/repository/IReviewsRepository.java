package com.uter.repository;

import com.uter.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("Select b from Reviews b where b.description =:descrip")
    public List<Reviews> findDescription(@Param("descrip") String description);
    @Query("Select c from Reviews c where c.stars =:star")
    public List<Reviews> findStars(@Param("star") int stars);
}