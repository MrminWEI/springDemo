package com.example.demo.biz.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.biz.user.entity.Detail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
public interface DetailService extends IService<Detail> {

  String queryDictTextByKey(String code,String key);

}
