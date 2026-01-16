package com.kersey.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.kersey.pojo.AlipayBean;
import com.kersey.pojo.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/alipay")
public class AliPayController {

    private String appId = "2021000122665015";
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClX2CPBeL08ngdbwgh83I/yg+R+bbH66vpGvEwET0wq1b7iPUDOh75jcJgXAadQ/xHTjUroDZ56Ek26KFxMQHGexS0o9fy+JPlUBq8EiOtaauIoZdnN91WLg9gfztE6QMIBsc3m9oxJ5zlVko+Wqcf7KKmcepQzxoEmg3sVLGQGTuYzRay+eBSbwVmIf9UWgYeNAThs8Jk/B3maaDN3uugGjxzd721qNyjICpM5LkMGAFuUS/M6moauSaWMtZum0aTFKmUBBPYn2TQv7M0eaLuXQJziceqKU0Lydu1yI4PryFp7ryra9iCAbqFFcYvB53rXnwkLyvzEWMFR4FIDuq1AgMBAAECggEAX5wO9WGP39I3g7zn1MktKpgl/F2FQXyNvY2oGAQ/fl3xQifij+UQd3NwB8nswQvHVWpv7zMHi9+yRJ6miAyvXBNY2z9G4OC6kP+JFWbfXakUVUAnirOPsf2bQk0M9UBKUl+o2guvUG/4k9cZwYcjzJ9BQj3mHKZAAp0Wo6YrqoUeGU3+R4w6NijLRQcDJtRfKMJhFggAO5wrkrq9E9jEieuRXdfdIJmPzreVCRRFAJOErP4YxYZLixhQqwNnHgga/AAOfbxH0S1Gt/B1rwZaEHAPz17PGwg+3ARHvc6RLTdJK1Ofi1vx3SoggE4AXgKUAHppy/5bvnbp7EA+TsppQQKBgQDUQphj3+nh+kIkBjFOKo7/iI/Bn6TxUZzHzMefNG0SaxCmxW+kT9PCXgJ8CdZk2eQ0vHlh8vKftbsxBRUnlmzNUZNW9wMQyqfXbYZu7v2rfIF6UZZny89v+G/x56TcoGEyGl2G5bZalOJO60wAvBYgsAN6ftj6mwoBGLkMoRZevQKBgQDHc0/ST/isDIKmZIFwoI9vMWglM+qIdeQ6Cp414F/cPi9UrHgngIP7hsdc1E9MQiTYkanuA7Om35uup/4dv3Hh1KqBo6WkMsgwzZ2TIrcvklP0x4TSs490wUfA9JhC6kEYVN9VzfSIy+v+K9FOkDSl0TI1nQMCfUruLdXvQqkXWQKBgF1OXQDiaESfJz4AvuHWkX/A/zilPfOa5alq2oGgoSXMjMhS2C3JrZOukbcG+AjjQ47vHwiwrHkKgRweWNtLI87pwjQjZ2SVQUzQ55ySUE5278/eV5iPHkPhWQxkwLL/iVpl/qETD6Mn+YeppYJZK/z4gr7xTd50EuTCgm4LzfKdAoGAY5DA7DBX5ytnnUDXDS5Z7Bwx7UB5eB3CDOd4C1UzBXNVlWNzp+ST/mBN7Z1e6KasViCBP/xBFrDRuuLKDHehlibf50+WRN3E6ff7DZRq9iiKGMvxFm3k01gZRc3UpLLkRA06YE8sH44NBRlCp4F9hrlj4ze6ZOe/HB9tiL8yQiECgYEAv5BAwX6TevYY6WVsR5XjYsVv/8JUTa5OLXl7lrZM880O7a5AgxPDhbJBb1+V1vS+g7pp00uYbg3MOYf8hW8bmBIPZXWWT39b0JQ5UNvi1gfKpGiAbjUvCmaNL/rdjOgoEL0kksCrewgQVQoH7HgbWvfdOjemiHWITvzfR3GH5S8=";
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsxivR6kbNuF6gVr7ZMcObKi+7KsNdRJbLje3ADfZ9o558rXJTQwv/8gmwSSMTeJPwcDJaSUcqgQ2SqbMfCVhFc1co/LSGjPO5LJNxdm5fqzwCbD8L6YDwhgdnsoNKS3SMDF+5xKu4E+56BKDCVe6/eOH9BwS7nd5XdRUWNmFcJmttr+e8iRNGJIanMHpQggyrhcleJ4QqJh9vM5TTvYiQ4SqD2IeEwWB7lUscQkN4JAUw4LF0jDhjJP4ErZMjsgoeS3rgJz5oE/2s1KSd3v0SKFToAR5ZQqIdONQa3KPofCIcVPDVrbXpMDsVVPRcTxk47gk1cGKI/1H6WFHFCI4cwIDAQAB";
    private String notifyUrl = "http://localhost:8080/success";
    private String returnUrl = "http://localhost:8080//pages/order/manage-2.html";
    private String charset = "UTF-8";
    private String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private final String format = "json";
    private String signType= "RSA2";



    @PostMapping("/pay")
    @ResponseBody
    public String pay(AlipayBean alipayBean, @RequestBody Order order) throws AlipayApiException {
        //模拟数据
//        alipayBean.setOut_trade_no(UUID.randomUUID().toString().replaceAll("-",""));
        alipayBean.setOut_trade_no(order.getOrderId().replaceAll("-",""));
        System.out.println(alipayBean.getOut_trade_no());
        alipayBean.setSubject("用户订单");
        alipayBean.setTotal_amount(Float.toString(order.getPrice()));
        System.out.println(alipayBean.getTotal_amount());
        alipayBean.setBody("用户创建的订单");

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, format, charset, publicKey, signType);
        //PC网页支付使用AlipayTradePagePayRequest传参，下面调用的是pageExecute方法
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
//        log.info("封装请求支付宝付款参数为:{}", JSON.toJSONString(alipayRequest));

        // 调用SDK生成表单
        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        log.info("请求支付宝付款返回参数为:{}", result);

        return result;
    }




    @RequestMapping(value = "/pages/order")
    public String payCoin(){
        return "manage-2.html";
    }

}
