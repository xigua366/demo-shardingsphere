package com.yx.demo.shardingsphere.controller;

import com.yx.demo.shardingsphere.domain.UserDO;
import com.yx.demo.shardingsphere.request.AddUserRequest;
import com.yx.demo.shardingsphere.service.UserService;
import com.yx.demo.shardingsphere.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询单个用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUser/{userId}")
    public JsonData getUser(@PathVariable("userId") Long userId) {
        UserDO user = userService.getUser(userId);
        return JsonData.buildSuccess(user);
    }

    /**
     * 查询多个用户信息
     * @param userIds
     * @return
     */
    @GetMapping("/listUser")
    public JsonData listUser(Long[] userIds) {
        List<UserDO> users = userService.listUser(Arrays.asList(userIds.clone()));
        return JsonData.buildSuccess(users);
    }

    /**
     * 查询某个范围的用户信息
     * @param minUserId
     * @param maxUserId
     * @return
     */
    @GetMapping("/listUserBetween")
    public JsonData listUserBetween(Long minUserId, Long maxUserId) {
        List<UserDO> users = userService.listUserBetween(minUserId, maxUserId);
        return JsonData.buildSuccess(users);
    }

    /**
     * 新增用户信息
     * @param addUserRequest
     * @return
     */
    @PostMapping
    public JsonData addUser(@RequestBody AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
        return JsonData.buildSuccess();
    }

    // TODO
    // 多条件分页查询用户信息

    // 根据用户名查询用户信息


}