package com.soap.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch模拟线程并发执行代码
 * 
 * @author gaopeng
 *
 */
public class CountDownLatchConTest {

    // 并发数
    private static final int THREAD_NUM = 5000;

    private static volatile CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < THREAD_NUM; i++) {
            int index = i+1;

            new Thread(() -> {
                /** 所有的线程在这里等待 **/
                try {
                    String port = "";
                    countDownLatch.await();
                    if (index%2 == 0) {
                        port = "9301";
                    } else {
                        port = "9302";
                    }

                    /** 执行业务逻辑 **/
                    String url = "http://127.0.0.1:"+ port +"/ms/business/goods/bugGoods";
                    HashMap<String, Object> requestMap = new HashMap<>();
                    requestMap.put("personId" , index);
                    requestMap.put("goodsId" , 1);
                    String body = HttpUtil.createPost(url).form(requestMap).execute().body();
                    System.out.println(body.substring(0,12) + "----" + port);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();



            /** 启动后，倒计时计数器减一，代表有一个线程准备就绪了 **/
            countDownLatch.countDown();
        }
    }
}