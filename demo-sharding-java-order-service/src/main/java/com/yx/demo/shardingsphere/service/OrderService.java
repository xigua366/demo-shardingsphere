package com.yx.demo.shardingsphere.service;

import com.yx.demo.shardingsphere.request.CreateOrderRequest;
import com.yx.demo.shardingsphere.vo.OrderVO;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface OrderService {

    /**
     * 新增订单
     * @param createOrderRequest
     * @return
     */
    void createOrder(CreateOrderRequest createOrderRequest);

    /**
     * 查询用户的订单列表
     * @param userId
     * @return
     */
    List<OrderVO> listOrder(Long userId);

    /**
     * 查询用户的订单列表
     * @param minUserId
     * @param maxUserId
     * @return
     */
    List<OrderVO> listOrderBetween(Long minUserId, Long maxUserId);

}