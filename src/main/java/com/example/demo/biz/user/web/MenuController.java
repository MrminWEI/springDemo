package com.example.demo.biz.user.web;

import com.example.demo.biz.user.entity.Menu;
import com.example.demo.biz.user.service.MenuService;
import com.example.demo.grcloud.base.BaseController;
import com.example.demo.grcloud.page.ObjectRestResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author minwei
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/user/menu")
public class MenuController extends BaseController<MenuService, Menu, Integer> {

  @Override
  @CacheEvict(value = "menuCache", allEntries = true)
  public ObjectRestResponse add(Menu menu) {
    return super.add(menu);
  }

  @Override
  @CacheEvict(value = "menuCache", allEntries = true)
  public ObjectRestResponse<Menu> delete(Integer id) {
    return super.delete(id);
  }

  @Override
  @CacheEvict(value = "menuCache", allEntries = true)
  public ObjectRestResponse<Menu> update(Menu menu) {
    return super.update(menu);
  }
}
