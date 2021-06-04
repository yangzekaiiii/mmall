package com.yzk.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzk.mmall.entity.Cart;
import com.yzk.mmall.entity.Product;
import com.yzk.mmall.entity.User;
import com.yzk.mmall.mapper.ProductMapper;
import com.yzk.mmall.service.CartService;
import com.yzk.mmall.service.ProductService;
import com.yzk.mmall.service.UserAddressService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.ccache.CCacheInputStream;

import javax.jws.WebParam;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ProductService productService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(quantity * price);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (cartService.save(cart)) {
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e) {
            return "redirect:/productCategory/list";
        }
        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        cartService.removeById(id);
        return "redirect:/cart/findAllCart";
    }

    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", user.getId());
        modelAndView.addObject("addressList", userAddressService.list(wrapper));
        return modelAndView;
    }

    @PostMapping("/add/{id}/{quantity}/{cost}")
    @ResponseBody
    public String addCart(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost
    ) {
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        Product product = productService.getById(cart.getProductId());
        Integer stock1 = product.getStock()-1;
        product.setStock(stock1);
        productService.updateById(product);
        if (cartService.updateById(cart)) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/sub/{id}/{quantity}/{cost}")
    @ResponseBody
    public String subCart(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost
    ) {
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        Product product = productService.getById(cart.getProductId());
        Integer stock1 = product.getStock()+1;
        product.setStock(stock1);
        productService.updateById(product);
        if (cartService.updateById(cart)) {
            return "success";
        } else {
            return "fail";
        }
    }
}


