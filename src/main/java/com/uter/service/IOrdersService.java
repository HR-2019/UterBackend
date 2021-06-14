package com.uter.service;

import com.uter.entities.Orders;

import java.util.Date;
import java.util.List;

public interface IOrdersService extends CrudService<Orders>{

    public List<Orders> findBetweenDates(Date fromDate, Date toDate) throws Exception;

}
