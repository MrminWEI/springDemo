package com.example.demo.biz.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.biz.user.entity.Menu;
import com.example.demo.biz.user.mapper.MenuMapper;
import com.example.demo.biz.user.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  @Autowired
  private MenuMapper mapper;

  @Override
  public List<Menu> getPermissionByUserId(Integer userId) {
    System.out.println("----------getPermissionByUserId--------");
    return mapper.getPermissionByUserId(userId);
  }
}
