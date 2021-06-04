package com.yzk.mmall.mapper;

import com.yzk.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzk.mmall.vo.ProductCategoryVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2021-04-11
 */

@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

}
