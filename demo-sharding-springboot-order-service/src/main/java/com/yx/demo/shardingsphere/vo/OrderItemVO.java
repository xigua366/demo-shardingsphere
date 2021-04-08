package com.yx.demo.shardingsphere.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class OrderItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单条目ID
     */
    private Integer itemId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品编号
     */
    private String productCode;

    /**
     * 购买数量
     */
    private Integer buyNum;

}