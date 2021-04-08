package com.yx.demo.shardingsphere.service;

import com.yx.demo.shardingsphere.domain.UserDO;
import com.yx.demo.shardingsphere.request.AddUserRequest;

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
     * 新增用户信息
     * @param addUserRequest
     */
    void addUser(AddUserRequest addUserRequest);

}