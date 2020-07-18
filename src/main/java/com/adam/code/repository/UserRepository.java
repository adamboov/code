package com.adam.code.repository;

import com.adam.code.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author VAIO-adam
 * 用户Repositor接口
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询用户实体
     * @return User    返回用户对象
     * @param userName 用户姓名
     */
    @Query(value = "select * from user where user_name = ?1", nativeQuery = true)
    User findByUserName(String userName);


    /**
     *  根据邮箱查询用户
     * @param email 邮箱
     * @return 返回用户对象
     */
    User findByEmail(String email);

}
