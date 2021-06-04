package com.yzk.mmall.service;

import com.yzk.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzk.mmall.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */
public interface OrderService extends IService<Orders> {

    boolean save(Orders orders, User user,String address,String remark);
}
