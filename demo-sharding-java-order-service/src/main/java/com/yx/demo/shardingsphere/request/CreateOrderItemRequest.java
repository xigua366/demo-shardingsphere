package com.yx.demo.shardingsphere.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class CreateOrderItemRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    private String productCode;

    /**
     * 购买数量
     */
    private Integer buyNum;

}