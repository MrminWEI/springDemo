package com.example.demo.biz.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Integer deptId;

    @TableField("DEPT_NO")
    private String deptNo;

    @TableField("DEPT_NAME")
    private String deptName;

    @TableField("STATUS")
    private String status;

    @TableField("DEPT_TYPE_ID")
    private String deptTypeId;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

}
