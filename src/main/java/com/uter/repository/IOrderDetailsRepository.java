package com.uter.repository;

import com.uter.entities.OrderDetails;
import com.uter.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Query("Select b from OrderDetails b where b.discount =:disct")
    public List<OrderDetails> findDiscount(@Param("disct") int discount);

}
