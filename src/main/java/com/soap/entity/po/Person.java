package com.soap.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangPY
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 姓
     */
    @TableField("f_name")
    private String fName;

    /**
     * 名
     */
    @TableField("l_name")
    private String lName;

    /**
     * 全名
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("sex")
    private Boolean sex;

    /**
     * 部门id
     */
    @TableField("d_id")
    private Long dId;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 描述
     */
    @TableField("describes")
    private String describes;

}
