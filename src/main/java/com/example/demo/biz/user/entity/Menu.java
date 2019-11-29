package com.example.demo.biz.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("t_s_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Integer menuId;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_DESC")
    private String menuDesc;

    @TableField("MENU_URL")
    private String menuUrl;

    @TableField("MENU_PID")
    private Integer menuPid;

    @TableField("MENU_TYPE")
    private Integer menuType;

    @TableField("MENU_STATUS")
    private Integer menuStatus;

    @TableField("MENU_LEVEL")
    private Integer menuLevel;

    @TableField("MENU_ICON")
    private String menuIcon;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;

    @TableField("MENU_ORDER")
    private Integer menuOrder;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
