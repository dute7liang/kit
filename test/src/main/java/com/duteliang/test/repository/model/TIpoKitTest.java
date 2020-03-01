package com.duteliang.test.repository.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * KIT测试表
 * </p>
 *
 * @author auto
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("KIT_TEST")
public class TIpoKitTest implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 字符串
     */
    @TableField(value = "THIS_NAME",jdbcType = JdbcType.OTHER)
    private String thisName;

    /**
     * 数字
     */
    @TableField("THIS_AGE")
    private Long thisAge;

    /**
     * 时间
     */
    @TableField("THIS_DATE")
    private Date thisDate;

    /**
     * 金额
     */
    @TableField(value = "THIS_PRICE")
    private BigDecimal thisPrice;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.UPDATE)
    private Date updateTime;


}
