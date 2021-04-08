package com.yx.demo.shardingsphere.service;

import com.yx.demo.shardingsphere.domain.UserDO;
import com.yx.demo.shardingsphere.request.AddUserRequest;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface UserService {

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    UserDO getUser(Long userId);

    /**
     * 根据ID查询用户信息
     * @param userIds
     * @return
     */
    List<UserDO> listUser(List<Long> userIds);

    /**
     * 根据ID查询某个范围的用户信息
     * @param minUserId
     * @param maxUserId
     * @return
     */
    List<UserDO> listUserBetween(Long minUserId, Long maxUserId);

    /**
     * 新增用户信息
     * @param addUserRequest
     */
    void addUser(AddUserRequest addUserRequest);

}