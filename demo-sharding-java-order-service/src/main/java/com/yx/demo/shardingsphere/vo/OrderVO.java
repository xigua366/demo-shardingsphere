package com.yx.demo.shardingsphere.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单状态
     */
    private String status;

    private List<OrderItemVO> orderItemList;
}