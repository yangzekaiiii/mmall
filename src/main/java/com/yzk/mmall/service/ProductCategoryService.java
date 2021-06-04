package com.yzk.mmall.service;

import com.yzk.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzk.mmall.vo.ProductCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */
@Service
public interface ProductCategoryService extends IService<ProductCategory> {

    public List<ProductCategoryVO> getAllProductCategoryVO();
}
