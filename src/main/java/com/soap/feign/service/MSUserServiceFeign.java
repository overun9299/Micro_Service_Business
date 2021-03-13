package com.soap.feign.service;


import com.soap.feign.service.hystrix.MSUserServiceFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

/**
 * Created by ZhangPY on 2020/6/7
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
@FeignClient(value = "micro-service-user" , fallback = MSUserServiceFeignHystrix.class)
public interface MSUserServiceFeign {




    /**
     * 通过id查询Person
     * @param personId
     * @return
     */
    @RequestMapping(value = "ms/user/person/getPersonById" , method = RequestMethod.GET)
    String getPersonById(@RequestParam("personId") Long personId);
}
