package com.adam.code.service;

import com.adam.code.entity.User;

/**
 * 用户service接口
 * @author VAIO-adam
 */
public interface IUserService {


    /**
     * 根据用户名查询用户实体
     * @return User    返回用户对象
     * @param userName 用户姓名
     */
    User findByUserName(String userName);


    /**
     *  根据邮箱查询用户
     * @param email 邮箱
     * @return 返回用户对象
     */
    User findByEmail(String email);

    /**
     * 添加或修改用户信息
     * @param user 用户对象
     */
    void save(User user);

}
