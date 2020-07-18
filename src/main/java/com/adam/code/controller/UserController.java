package com.adam.code.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author VAIO-adam
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户注册
     */
    public Map<String, Object> register() {
//        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);
        map.put("success", true);
        return map;
    }
}
