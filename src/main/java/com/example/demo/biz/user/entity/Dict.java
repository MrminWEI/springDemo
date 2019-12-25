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
@TableName("t_s_dict")
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DICT_ID", type = IdType.AUTO)
    private Integer dictId;

    @TableField("DICT_NAME")
    private String dictName;

    @TableField("DICT_DESC")
    private String dictDesc;

    @TableField("DICT_STATUS")
    private Integer dictStatus;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }

}
