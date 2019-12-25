package com.example.demo.biz.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.biz.user.entity.Menu;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
public interface MenuService extends IService<Menu> {

  /**
   * 缓存目录 目录跟新 @CacheEvict 清除缓存
   *
   * @param userId :
   * @return : java.util.List<com.example.demo.biz.user.entity.Menu>
   * @author : minwei
   * @date : 2019/12/11 10:46
   */
  @Cacheable(value = "menuCache", key = "#userId")
  List<Menu> getPermissionByUserId(Integer userId);

}
