package com.erp.exception;

/**
 * 订单异常
 */
public class OrderNotFoundException extends BaseException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
