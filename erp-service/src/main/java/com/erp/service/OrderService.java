package com.erp.service;

import com.erp.dto.OrderSubmitDTO;

public interface OrderService {

    /**
     * 删除订单
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 添加订单
     * @param orderSubmitDTO
     */
    void submit(OrderSubmitDTO orderSubmitDTO);
}
