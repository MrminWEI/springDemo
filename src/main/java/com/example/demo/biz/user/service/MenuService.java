package com.example.demo.biz.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.biz.user.entity.Menu;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
public interface MenuService extends IService<Menu> {

  List<Menu> getPermissionByUserId(Integer userId);

}
