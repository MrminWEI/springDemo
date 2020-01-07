package com.example.demo.biz.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.biz.user.entity.Detail;
import com.example.demo.biz.user.mapper.DetailMapper;
import com.example.demo.biz.user.service.DetailService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@Service
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements
    DetailService {

  @Resource
  DetailMapper mapper;

  @Override
  public  String queryDictTextByKey(String code,String key){
    return  mapper.queryDictTextByKey(code,key);
  }
}
