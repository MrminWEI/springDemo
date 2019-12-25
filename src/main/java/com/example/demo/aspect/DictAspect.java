package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.Dept;
import com.example.demo.annotation.Dict;
import com.example.demo.biz.user.entity.Department;
import com.example.demo.biz.user.service.DepartmentService;
import com.example.demo.biz.user.service.DetailService;
import com.example.demo.grcloud.page.GridDataModel;
import com.example.demo.utils.CommonConstant;
import com.example.demo.utils.oConvertUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * DictAspect
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class DictAspect {

  @Autowired
  DetailService detailService;

  @Autowired
  DepartmentService departmentService;

  private Logger logger = LoggerFactory.getLogger(DictAspect.class);

  @Pointcut("execution(public com.example.demo.grcloud.page.GridDataModel *(..))")
  public void excuDict() {
  }

  @Around("excuDict()")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

    long time1 = System.currentTimeMillis();
    Object result = pjp.proceed();
    long time2 = System.currentTimeMillis();
    //  logger.info("获取JSON数据 耗时：" + (time2 - time1) + "ms");
    long start = System.currentTimeMillis();
    this.parseDictText(result);
    long end = System.currentTimeMillis();
    //  logger.info("解析注入JSON数据  耗时" + (end - start) + "ms");
    return result;
  }

  private void parseDictText(Object result) {

    if (result instanceof GridDataModel) {
      List<JSONObject> items = new ArrayList<>();
      for (Object record : ((GridDataModel) result).getRows()
          ) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{}";
        try {
          json = mapper.writeValueAsString(record);
        } catch (JsonProcessingException e) {
          logger.error("json解析失败：" + e.getMessage(), e);
        }

        JSONObject item = JSONObject.parseObject(json);
        for (Field field : oConvertUtils.getAllFields(record)
            ) {
          if (field.getAnnotation(Dict.class) != null) {
            String code = field.getAnnotation(Dict.class).dicCode();
            String text = field.getAnnotation(Dict.class).dicText();
            String table = field.getAnnotation(Dict.class).dictTable();
            String key = String.valueOf(item.get(field.getName()));
            //翻译字典值对应的txt
            String textValue = translateDictValue(code, text, table, key);

            item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
          }
          if (field.getAnnotation(Dept.class) != null) {
            String key = String.valueOf(item.get(field.getName()));
            String textValue = translateDeptValue(key);
            item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
          }
          //date类型默认转换string格式化日期
          if (field.getType().getName().equals("java.util.Date")
              && field.getAnnotation(JsonFormat.class) == null
              && item.get(field.getName()) != null) {
            SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
          }

        }
        items.add(item);
      }

      ((GridDataModel) result).setRows(items);
    }
  }


  private String translateDictValue(String code, String text, String table, String key) {

    if (oConvertUtils.isEmpty(key)) {
      return null;
    }
    StringBuffer textValue = new StringBuffer();
    String[] keys = key.split(",");
    for (String k : keys
        ) {

      String tmpValue = null;
      if (k.trim().length() == 0) {
        continue; //跳过循环
      }
      if (oConvertUtils.isNotEmpty(table)) {

      } else {
        tmpValue = detailService.queryDictTextByKey(code, key);
      }
      if (tmpValue != null) {
        if (!"".equals(textValue.toString())) {
          textValue.append(",");
        }
        textValue.append(tmpValue);
      }

    }
    return textValue.toString();

  }

  private String translateDeptValue(String key) {
    String textValue = null;
    if (oConvertUtils.isEmpty(key)) {
      return null;
    }
    Department department = departmentService.selectById(key);
    if (department != null) {
      textValue = department.getDeptName();
    }
    return textValue;
  }
}
