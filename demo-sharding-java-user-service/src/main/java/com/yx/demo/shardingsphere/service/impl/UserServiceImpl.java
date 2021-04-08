package com.yx.demo.shardingsphere.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yx.demo.shardingsphere.domain.UserDO;
import com.yx.demo.shardingsphere.mapper.UserMapper;
import com.yx.demo.shardingsphere.request.AddUserRequest;
import com.yx.demo.shardingsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    @Override
    public UserDO getUser(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public List<UserDO> listUser(List<Long> userIds) {
        return userMapper.selectList(new QueryWrapper<UserDO>().in("user_id", userIds));
    }

    @Override
    public List<UserDO> listUserBetween(Long minUserId, Long maxUserId) {
        return userMapper.selectList(new QueryWrapper<UserDO>().between("user_id", minUserId, maxUserId));
    }

    /**
     * 新增用户信息
     * @param addUserRequest
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(AddUserRequest addUserRequest) {
        String username = addUserRequest.getUsername();
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userMapper.insert(userDO);
    }
}