package com.erp.service.Impl;

import com.erp.exception.OrderNotFoundException;
import com.erp.mapper.OrderDetailMapper;
import com.erp.mapper.OrderMapper;
import com.erp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 根据id删除订单
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {

        //1.删除订单明细
        orderDetailMapper.deleteByOrderId(id);

        //2.删除订单
        int rows = orderMapper.deleteById(id);

        //3.判断删除结果
        if (rows == 0){
            throw new OrderNotFoundException("订单不存在");
        }

    }
}
