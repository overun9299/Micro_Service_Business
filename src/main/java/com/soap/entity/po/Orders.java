package com.soap.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangPY
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId("id")
    private String id;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @TableField("goods_title")
    private String goodsTitle;

    /**
     * 商品价格
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    /**
     * 抢购人id
     */
    @TableField("person_id")
    private Long personId;

    /**
     * 抢购人姓名
     */
    @TableField("person_name")
    private String personName;

    /**
     * 抢购人手机
     */
    @TableField("person_phone")
    private String personPhone;

    /**
     * 订单地址
     */
    @TableField("address")
    private String address;

    /**
     * 订单状态(0：未完成  1：已完成)
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除(0:否   1：是)
     */
    @TableField("is_delete")
    private Integer isDelete;


}
