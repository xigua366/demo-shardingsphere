package com.yx.demo.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.demo.shardingsphere.domain.OrderDO;
import com.yx.demo.shardingsphere.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xi.yang
 * @since 2021-04-07
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

    /**
     * 查询某个范围内的用户订单例表
     * @param minUserId
     * @param maxUserId
     * @return
     */
    List<OrderVO> listOrderBetween(@Param("minUserId") Long minUserId, @Param("maxUserId") Long maxUserId);

}
