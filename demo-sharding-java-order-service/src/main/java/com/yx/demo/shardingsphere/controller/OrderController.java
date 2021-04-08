package com.yx.demo.shardingsphere.controller;

import com.yx.demo.shardingsphere.request.CreateOrderRequest;
import com.yx.demo.shardingsphere.service.OrderService;
import com.yx.demo.shardingsphere.utils.JsonData;
import com.yx.demo.shardingsphere.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     * @param createOrderRequest
     * @return
     */
    @PostMapping("/createOrder")
    public JsonData createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return JsonData.buildSuccess();
    }

    /**
     * 查询用户的订单列表
     * @param userId
     * @return
     */
    @GetMapping("/listOrder")
    public JsonData listOrder(Long userId) {
        List<OrderVO> orderVOList = orderService.listOrder(userId);
        return JsonData.buildSuccess(orderVOList);
    }

    /**
     * 查询用户的订单列表
     * @param minUserId
     * @param maxUserId
     * @return
     */
    @GetMapping("/listOrderBetween")
    public JsonData listOrderBetween(Long minUserId, Long maxUserId) {
        List<OrderVO> orderVOList = orderService.listOrderBetween(minUserId, maxUserId);
        return JsonData.buildSuccess(orderVOList);
    }

    // TODO
    // 多条件查询分页查询用户订单例表

    // 直接根据订单ID查询订单详情（没有用户ID参数）

}