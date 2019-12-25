package com.example.demo.biz.user.web;


import com.example.demo.biz.user.entity.Dict;
import com.example.demo.biz.user.service.DictService;
import com.example.demo.grcloud.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/user/sDict")
public class DictController extends BaseController<DictService,Dict,Integer> {
}
