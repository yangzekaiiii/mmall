package com.yzk.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzk.mmall.entity.User;
import com.yzk.mmall.service.CartService;
import com.yzk.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/register")
        public String register(User user, Model model){
            boolean result = false;
            try {
                result = userService.save(user);
            }catch (Exception e){
                model.addAttribute("error",user.getLoginName()+"用户名已存在！请重新输入！");
                return "register";
            }
            return "login";
    }

    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return "login";
        }else {
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    //用户信息界面
    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

