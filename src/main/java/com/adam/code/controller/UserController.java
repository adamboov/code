package com.adam.code.controller;

import com.adam.code.entity.User;
import com.adam.code.service.IUserService;
import com.adam.code.util.CryptographyUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
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
            iUserService.save(user);
            map.put("success", true);
        }

        return map;
    }
}
