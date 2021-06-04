package com.yzk.mmall.service;

import com.yzk.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzk.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */
public interface CartService extends IService<Cart> {

    public List<CartVO> findAllCartVOByUserId(Integer id);

}
