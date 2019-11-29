package com.example.demo.commom;


import lombok.Data;
import java.io.Serializable;

@Data
public class FrontUser implements Serializable {

    public Long userId;

    public String userNo;

    private String realName;

    private String email;

    private String mobile;

    private Integer sex;

    private Long deptId;

    private String enterprisesName;

}
