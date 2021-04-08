package com.yx.demo.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.demo.shardingsphere.domain.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xi.yang
 * @since 2021-04-07
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemDO> {

}
