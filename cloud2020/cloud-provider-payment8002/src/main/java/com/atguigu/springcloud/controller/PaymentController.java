package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int count= paymentService.create(payment);
        CommonResult result = null;

        if(count>0){
            result =new CommonResult(200,"插入成功！服务端口"+port,count);
        }else {
            result =new CommonResult(444,"插入失败！",null);
        }
        return  result;
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult result = null;
        Payment payment = paymentService.getPaymentById(id);
        log.info("123123hjkhkjh   8002");
        if(payment!=null){
            result =new CommonResult(200,"查询成功！服务端口"+port,payment);
        }else {
            result =new CommonResult(444,"查询失败！没有对应的id"+id,null);
        }
        return  result;
    }
}
