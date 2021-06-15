package com.uter.repository;

import com.uter.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {

    @Query("Select o from Orders o where o.orderDate =:fromDate and o.orderDate =:toDate")

    public List<Orders> findBetweenDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
