package com.example.demo.biz.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author minwei
 * @since 2019-11-29
 */
@Data
@Accessors(chain = true)
@TableName("t_s_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Integer roleId;

    @TableField("ROLE_NAME")
    private String roleName;

    @TableField("ROLE_DESC")
    private String roleDesc;

    @TableField("ROLE_ORDER")
    private Integer roleOrder;

    @TableField("ROLE_TYPE")
    private Integer roleType;

    @TableField("ROLE_STATUS")
    private Integer roleStatus;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
