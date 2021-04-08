package com.yx.demo.shardingsphere.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class AddUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;
}