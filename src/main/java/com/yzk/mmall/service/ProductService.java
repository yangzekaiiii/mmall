package com.yzk.mmall.service;

import com.yzk.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableInterceptor.Interceptor;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */
public interface ProductService extends IService<Product> {

    public List<Product> findByCategoryId(String type,Integer categoryId);
}
