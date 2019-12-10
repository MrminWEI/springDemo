package com.example.demo.commom;


import com.example.demo.biz.user.entity.Menu;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FrontUser implements Serializable {

    private Integer userId;

    private String userName;

    private Integer userOrder;

    private Integer userType;

    private Integer userStatus;

    private String userPwd;

    private Date createTime;

    private Date updateTime;

    private String realName;

    private String email;

    private String mobile;

    private Integer sex;

    private Integer deptId;

    private List<Menu> menus;
}
