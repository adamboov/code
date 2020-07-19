package com.adam.code.controller;

import com.adam.code.entity.User;
import com.adam.code.service.IUserService;
import com.adam.code.util.CryptographyUtil;
import com.adam.code.util.StringUtils;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层
 * @author VAIO-adam
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${personConfig.salt}")
    private String salt;

    @Autowired
    private IUserService iUserService;

    /**
     * 用户注册
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public Map<String, Object> register(@Valid User user, BindingResult bindingResult) {
//        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);
        if (bindingResult.hasErrors()) {
            map.put("success", false);
            map.put("errorInfo", bindingResult.getFieldError().getDefaultMessage());

        } else if (iUserService.findByUserName(user.getUserName()) != null) {
            map.put("success", false);
            map.put("errorInfo", "用户名已存在，请更换！");

        } else if (iUserService.findByEmail(user.getEmail()) != null) {
            map.put("success", false);
            map.put("errorInfo", "邮箱已存在，请更换！");

        } else {
            user.setPassword(CryptographyUtil.MD5(user.getPassword(), salt));
            user.setCreateDate(new Date());
            user.setLastDate(new Date());
            user.setHeadPortrait("tou.jpg");
            iUserService.save(user);
            map.put("success", true);
        }

        return map;
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@Valid User user, HttpSession session) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);

        if (StringUtils.isEmpty(user.getUserName())) {

            map.put("success", false);
            map.put("errorInfo", "请输入用户名！");
        } else if (StringUtils.isEmpty(user.getPassword())) {

            map.put("success", false);
            map.put("errorInfo", "请输入用户名！");
        } else {
            //  登录验证
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), CryptographyUtil.MD5(user.getPassword(), salt));
            subject.login(token);
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User currentUser = iUserService.findByUserName(userName);
            if (currentUser.isOff()) {
                map.put("success", false);
                map.put("errorInfo", "该用户已被暂停使用，请联系管理员！");
            }
        }
            return null;
    }
}
