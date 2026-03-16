package com.erp.service;

import com.erp.dto.OrderSubmitDTO;
import com.erp.entity.SaleOrder;
import com.erp.vo.admin.AdminOrderDetailVO;
import com.erp.vo.employee.EmployeeOrderDetailVO;

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
     * 管理员根据id查询订单信息
     * @param id
     * @return
     */
    AdminOrderDetailVO getAdminOrderDetailById(Integer id);

    /**
     * 员工根据id查询订单信息
     * @param id
     * @return
     */
    EmployeeOrderDetailVO getEmployeeOrderDetailById(Integer id);
}
