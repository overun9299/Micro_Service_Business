package com.soap.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by ZhangPY on 2021/1/24
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: PageHelper
 */
@Data
@Accessors(chain = true)
public class PageHelper {

    /**
     * 分页偏移量
     */
    private Integer page = 1;

    /**
     * 分页查询数量
     */
    private Integer limit = 10;
}
