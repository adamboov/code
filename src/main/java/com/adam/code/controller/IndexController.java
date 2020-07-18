package com.adam.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author VAIO-adam
 */

@Controller
public class IndexController {

    /**
     * 首页
     */
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");

        return mav;
    }
}
