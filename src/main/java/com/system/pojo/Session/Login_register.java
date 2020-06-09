package com.system.pojo.Session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录权限表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Login_register {
    private String id;
    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //权限状态
    private String limits_state;
    //权限类型
    private String limits_type;
}
