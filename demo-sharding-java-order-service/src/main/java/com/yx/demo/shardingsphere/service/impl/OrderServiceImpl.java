package com.yx.demo.shardingsphere.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yx.demo.shardingsphere.domain.OrderDO;
import com.yx.demo.shardingsphere.domain.OrderItemDO;
import com.yx.demo.shardingsphere.mapper.OrderItemMapper;
import com.yx.demo.shardingsphere.mapper.OrderMapper;
import com.yx.demo.shardingsphere.request.CreateOrderItemRequest;
import com.yx.demo.shardingsphere.request.CreateOrderRequest;
import com.yx.demo.shardingsphere.service.OrderService;
import com.yx.demo.shardingsphere.vo.OrderItemVO;
import com.yx.demo.shardingsphere.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 新增订单
     * @param createOrderRequest
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(CreateOrderRequest createOrderRequest) {

        Long userId = createOrderRequest.getUserId();
        String status = createOrderRequest.getStatus();

        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setStatus(status);
        orderMapper.insert(orderDO);
        Long orderId = orderDO.getOrderId();

        List<CreateOrderItemRequest> createOrderItemRequestList = createOrderRequest.getOrderItemRequestList();
        for(CreateOrderItemRequest createOrderItemRequest : createOrderItemRequestList) {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(orderId);
            orderItemDO.setUserId(userId);
            orderItemDO.setProductCode(createOrderItemRequest.getProductCode());
            orderItemDO.setBuyNum(createOrderItemRequest.getBuyNum());
            orderItemMapper.insert(orderItemDO);
        }
    }

    /**
     * 查询订单
     * @param userId
     * @return
     */
    @Override
    public List<OrderVO> listOrder(Long userId) {

        List<OrderDO> orderList = orderMapper.selectList(new QueryWrapper<OrderDO>().eq("user_id", userId));

        List<OrderVO> orderVOList = null;

        // TODO 这里改成多表关联查询，可以测试一下绑定表的效果
        if(orderList != null && !orderList.isEmpty()) {
            orderVOList = new ArrayList<>(orderList.size());
            for(OrderDO orderDO : orderList) {

                OrderVO orderVO = new OrderVO();
                orderVO.setOrderId(orderDO.getOrderId());
                orderVO.setUserId(userId);
                orderVO.setStatus(orderDO.getStatus());

                List<OrderItemDO> orderItemList = orderItemMapper.selectList(new QueryWrapper<OrderItemDO>().eq("user_id", userId).eq("order_id", orderDO.getOrderId()));
                if(orderItemList != null && !orderItemList.isEmpty()) {
                    List<OrderItemVO> orderItemVOList = new ArrayList<>();
                    for(OrderItemDO orderItemDO : orderItemList) {
                        OrderItemVO orderItemVO = new OrderItemVO();
                        orderItemVO.setOrderId(orderItemDO.getOrderId());
                        orderItemVO.setUserId(orderItemDO.getUserId());
                        orderItemVO.setProductCode(orderItemDO.getProductCode());
                        orderItemVO.setBuyNum(orderItemDO.getBuyNum());
                        orderItemVOList.add(orderItemVO);
                    }
                    orderVO.setOrderItemList(orderItemVOList);
                }
                orderVOList.add(orderVO);
            }
        }
        return orderVOList;
    }
}