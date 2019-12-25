package com.example.demo.biz.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.biz.user.entity.Department;
import com.example.demo.biz.user.mapper.DepartmentMapper;
import com.example.demo.biz.user.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements
    DepartmentService {

}
