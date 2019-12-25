package com.example.demo.biz.user.web;


import com.example.demo.biz.user.entity.Department;
import com.example.demo.biz.user.service.DepartmentService;
import com.example.demo.grcloud.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/user/department")
public class DepartmentController extends BaseController<DepartmentService,Department,Integer> {
}
