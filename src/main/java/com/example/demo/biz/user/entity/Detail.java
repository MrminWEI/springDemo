package com.example.demo.biz.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author minwei
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@TableName("t_s_detail")
public class Detail extends Model<Detail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detail_id",type = IdType.AUTO)
    private Integer detailId;

    @TableField("dict_id")
    private Integer dictId;

    @TableField("detail_name")
    private String detailName;

    @TableField("detail_value")
    private String detailValue;

    @TableField("detail_desc")
    private String detailDesc;

    @TableField("detail_status")
    private Integer detailStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.detailId;
    }

}
