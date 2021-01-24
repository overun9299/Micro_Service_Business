package com.soap.controller;


import com.soap.entity.query.GoodsQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangPY
 * @since 2021-01-24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    public String getGoodsListByPage(HttpServletRequest request, GoodsQuery goodsQuery) {

        return null;
    }
}
