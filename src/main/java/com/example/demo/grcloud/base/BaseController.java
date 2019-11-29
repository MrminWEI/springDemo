package com.example.demo.grcloud.base;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo.commom.CommonUtils;
import com.example.demo.grcloud.page.GridDataModel;
import com.example.demo.grcloud.page.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController<Biz extends IService<Entity>, Entity, PK> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz biz;

    private Class entityClass;

    public Class getEntityClass() {
        if(entityClass == null){
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type[] types = type.getActualTypeArguments();
            // 4 获取第2个参数(泛型的具体类)
            this.entityClass = (Class) types[1];
        }
        return entityClass;
    }

    /**
     * 公共方法添加
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse add(@RequestBody Entity entity) {
        return new ObjectRestResponse<Entity>().rel(biz.insert(preHandler(entity)));
    }
    /**
     * 公共方法，根据主键查询实体
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable PK id) {
        return new ObjectRestResponse<Entity>().rel(true)
                .data(biz.selectById(id.toString()));
    }
    /**
     * 公共方法，根据实体修改
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(@RequestBody Entity entity) {
        return new ObjectRestResponse<>().rel(biz.updateById(preHandler(entity)));
    }

    /**
     * 公共方法,根据主键删除
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> delete(@PathVariable PK id) {
        return new ObjectRestResponse<Entity>().rel(biz.deleteById((Serializable) id));
    }

    /**
     * 公共方法,批量删除
     */
    @RequestMapping(value = "/delbatch/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> deleteBatch(@PathVariable String ids) {
        List<Serializable> idList =new ArrayList<Serializable>();
        String[] instIdArr = ids.split(",");
        for (String instId : instIdArr) {
            idList.add(instId);
        }

        return new ObjectRestResponse<Entity>().rel( biz.deleteBatchIds(idList));
    }

    /**
     * 公共方法 查询所有
     */
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> listAll(@RequestParam Map<String, Object> params){
        List<Entity> data = null;
        Wrapper condition = convertParams(params);
        data = biz.selectList(condition);
        return new ObjectRestResponse<>().rel(true).data(data);
    }

    /**
     * 公共方法 分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public GridDataModel page(@RequestParam Map<String, Object> params) {
        /*current:当前页，size:每页显示条数*/
        Page<Entity> page = getPage(params);
        Wrapper condition = convertParams(params);
        page = biz.selectPage(page,condition);
        int total = biz.selectCount(condition);
        page.setTotal(total);

        GridDataModel data = new GridDataModel(page.getRecords(),total);

        return data;
    }

    public Page<Entity> getPage(Map<String, Object> params) {
        //当前页
        int currentPage = params.get("page") == null ? 1 : Integer.valueOf(params.get("page").toString());
        //每页显示数量
        int size = params.get("limit") == null ? 10 : Integer.valueOf(params.get("limit").toString());

        Page<Entity> page = new Page(currentPage, size);
        params.remove("limit");
        params.remove("page");
        //添加默认排序字段
        if (params.get("orderByField") != null && StringUtils.isNotBlank(params.get("orderByField").toString())) {
            String orderByField = params.get("orderByField").toString();
            page.setOrderByField(orderByField);
        }
        //指定升降序
        if (params.get("isAsc") != null) {
            Boolean isAsc =Boolean.valueOf(params.get("isAsc").toString());
            page.setAsc(isAsc);
        }
        return page;
    }

    /**
     *查询变量约定：
     * 模糊查询 变量加后缀"Like",如：userNameLike
     * 大于等于查询 变量加后缀"Ge",如：userNameGe
     * 大于查询 变量加后缀"Gt",如：userNameGt
     * 小于等于查询 变量加后缀"Le",如：userNameLe
     * 小于查询 变量加后缀"Lt",如：userNameLt
     * 未做约定则默认为精确查询(=)
     * @param params
     * @return
     */
    public Wrapper  convertParams(Map<String,Object> params){
        Wrapper<Entity> condition = Condition.<Entity>wrapper();

        for(Map.Entry<String, Object> entry : params.entrySet()){
            if(entry.getValue() == null){
                continue;
            }
            if(entry.getValue() instanceof String && StringUtils.isBlank((String)entry.getValue())){
                continue;
            }
            if(StringUtils.endsWith(entry.getKey(), "Like")){//模糊查询
                String prop = StringUtils.substringBefore(entry.getKey(),"Like");
                if(BeanUtils.getPropertyDescriptor(getEntityClass(), prop) == null){
                    //过滤掉不是entity里面属性的参数
                    continue;
                }
                condition.like(CommonUtils.underscoreName(prop), entry.getValue().toString());
            }else if(StringUtils.endsWith(entry.getKey(), "Le")) {//小于等于
                String prop = StringUtils.substringBefore(entry.getKey(),"Le");
                if(BeanUtils.getPropertyDescriptor(getEntityClass(), prop) == null){
                    //过滤掉不是entity里面属性的参数
                    continue;
                }
                condition.le(CommonUtils.underscoreName(prop), entry.getValue());
            }else if(StringUtils.endsWith(entry.getKey(), "Lt")) {//小于
                String prop = StringUtils.substringBefore(entry.getKey(),"Lt");
                if(BeanUtils.getPropertyDescriptor(getEntityClass(), prop) == null){
                    //过滤掉不是entity里面属性的参数
                    continue;
                }
                condition.lt(CommonUtils.underscoreName(prop), entry.getValue());
            }else if(StringUtils.endsWith(entry.getKey(), "Ge")) {//大于等于
                String prop = StringUtils.substringBefore(entry.getKey(),"Ge");
                if(BeanUtils.getPropertyDescriptor(getEntityClass(), prop) == null){
                    //过滤掉不是entity里面属性的参数
                    continue;
                }
                condition.ge(CommonUtils.underscoreName(prop), entry.getValue());
            }else if(StringUtils.endsWith(entry.getKey(), "Gt")) {//大于
                String prop = StringUtils.substringBefore(entry.getKey(),"Gt");
                if(BeanUtils.getPropertyDescriptor(getEntityClass(), prop) == null){
                    //过滤掉不是entity里面属性的参数
                    continue;
                }
                condition.gt(CommonUtils.underscoreName(prop), entry.getValue());

            }else{
                condition.eq(CommonUtils.underscoreName(entry.getKey()), entry.getValue());
            }
        }
        return condition;
    }


    /**
     * 实体预处理
     *
     * @param entity
     * @return
     */
    protected Entity preHandler(Entity entity) {
        return entity;
    }
}
