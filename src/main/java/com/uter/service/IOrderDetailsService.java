package com.uter.service;

import com.uter.entities.OrderDetails;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailsService extends CrudService<OrderDetails> {

    public List<OrderDetails> findDiscount(int discount) throws Exception;

}
