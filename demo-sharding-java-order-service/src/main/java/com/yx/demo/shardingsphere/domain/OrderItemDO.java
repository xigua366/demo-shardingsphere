package com.yx.demo.shardingsphere.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xi.yang
 * @since 2021-04-07
 */
@TableName("t_order_item")
public class OrderItemDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.NONE)
    private Long itemId;

    private Long orderId;

    private Long userId;

    private String productCode;

    private Integer buyNum;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "OrderItemDO{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", productCode='" + productCode + '\'' +
                ", buyNum=" + buyNum +
                '}';
    }
}
