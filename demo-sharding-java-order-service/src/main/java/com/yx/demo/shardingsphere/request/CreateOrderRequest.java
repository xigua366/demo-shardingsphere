package com.yx.demo.shardingsphere.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class CreateOrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单条目
     */
    private List<CreateOrderItemRequest> orderItemRequestList;

}