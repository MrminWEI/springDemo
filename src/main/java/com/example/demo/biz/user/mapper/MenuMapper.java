package com.example.demo.biz.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.biz.user.entity.Menu;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
public interface MenuMapper extends BaseMapper<Menu> {

  List<Menu> getPermissionByUserId(Integer userId);

}
