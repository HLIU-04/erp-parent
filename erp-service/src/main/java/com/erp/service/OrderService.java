package com.erp.service;

import com.erp.dto.OrderSubmitDTO;
import com.erp.entity.SaleOrder;
import com.erp.vo.admin.AdminOrderDetailVO;

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

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
    AdminOrderDetailVO getAdminOrderDetailById(Integer id);
}
