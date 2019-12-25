package com.example.demo.biz.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.biz.user.entity.Detail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
public interface DetailMapper extends BaseMapper<Detail> {

  @Select(" SELECT  a.detail_name  FROM   T_S_DETAIL  a  WHERE   a.dict_id  =(SELECT  b.DICT_ID   from " +
      "  T_S_DICT  b   WHERE  b.dict_name=#{code}  ) and a.detail_value =#{key} ")
  String  queryDictTextByKey(@Param("code") String code , @Param("key") String key);

}
