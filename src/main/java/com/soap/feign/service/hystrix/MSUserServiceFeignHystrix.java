package com.soap.feign.service.hystrix;

import com.soap.commons.ReturnCommons;
import com.soap.feign.service.MSUserServiceFeign;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangPY on 2021/2/21
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: MSUserServiceFeignHystrix
 */
@Component
public class MSUserServiceFeignHystrix implements MSUserServiceFeign {

    @Override
    public String getPersonById(Long personId) {
        return ReturnCommons.getFail("服务异常");
    }
}
