package com.adam.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author VAIO-adam
 * 用户实体类
 * @JsonIgnoreProperties 注解会忽略类中不存在的字段
 */

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "hander", "fielHandler"})
public class User implements Serializable {

    /**
     * 用户id 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * openId QQ用户标识
     */
    @Column(length = 128)
    private String openId;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空!")
    @Column(length = 64)
    private String nickname;

    /**
     * 用户名
     */
    @NotEmpty(message = "请输入用户名!")
    @Column(length = 64)
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message = "请输入密码！")
    @Column(length = 128)
    private String password;

    /**
     * 邮箱地址
     */
    @NotEmpty(message = "请输入邮箱地址！")
    @Email(message = "邮箱格式有误！")
    @Column(length = 64)
    private String email;

    /**
     * 用户头像
     */
    @Column
    private String headPortrait;

    @Column(length = 8)
    private String sex;

    /**
     * 积分
     */
    private Integer points = 0;

    /**
     * 是否VIP
     */
    private boolean VIP = false;

    private Integer VIPGrade = 0;

    /**
     * 账号是否被封禁
     */
    private boolean off = false;

    /**
     * 角色名称 管理员  会员
     */
    private String roleName;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 上一次登录时间
     */
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastDate;

    /**
     * 消息条数     不是数据库字段
     */
    @Transient
    private Integer messageCount;



}
