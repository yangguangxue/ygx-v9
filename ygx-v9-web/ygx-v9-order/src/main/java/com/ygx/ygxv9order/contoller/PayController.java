package com.ygx.ygxv9order.contoller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("order")
public class PayController {

    @RequestMapping("pay")
    public void pay(HttpServletRequest httpRequest,
                    HttpServletResponse httpResponse,
                    String orderNo) throws ServletException, IOException

    {
            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipaydev.com/gateway.do",
                    "2016092700605356",
                    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuXCts0h8UA2ts43JcNt3FCpzblLy73dbo2uLsHi/37HjNk6Wqt9L89pbwKZPXagQ3347LDak2IkfOq3BykXOWlE2LgxXQQ9YwF60zMf2k5E3wc13bDDSBxt+99iLgWdNkrVDrTdDqg/DgJYX73oLhniXzypc6emIvkMHod95+MRMtKFmwyQluK5t+rEveWq6m1ZjbXN3iqTnhny6Q018TdrVIQF+5qSiPcoNb1G7oYSkpYas8WP1xUQ9e2OM0E8vDrPdQtntIiY0U/gteJLz8CebuJHwWjR/k45/02gWQ0S43C+eD+TePXmmC+zDXy2JIkGT302SCKpi//TIYXVCPAgMBAAECggEAU0SNMIEAeoxPh0bv3H4PVwSEeJEFuSpucfHmoSChg+MYpgS2/1qellQlLTiN9DgbH5vSE8LIZqPkaxyQrQYtj2Hvzoqhpj5fUcRc4LAlE9Q8LHLMBcpEYR3ayFt9uGrte40Rse4NsZGlPUZuQCS5mInwUgcURccgkNqu4Bgu11BHz2/skcSNSYoB/LMh6n8MY1XeBv/me9m7th+T8lvNSLSwjKCodb4pdB90kF7Y1TLbJXmVI3bNY6Fmb+2aPd6cfWve5zQQXgKCiA/jo2ZvGGy93vVr/VCgLXnStVFJJWiwpa0CMf044PNbqTIrodGY1zxwy5Rk6I7fcdWU2QqksQKBgQD6DUP5gPwSY4rxtfWgkeTBRlq3hqs6tau/uTwIX5tSvOXI8RWZsKuinQbsQZS8QO4Mu8bS1Mm3O6Zz+bGQUxonfnQddKzVDPx8naPYMc6KROSgVyd/cP2zv1b4LqLOlEpbx1Z3jB00gS34YLV2NbFVi5dRcF1K00bKRfX1m+4xWwKBgQCygfc4mcwGH6eN3rkzISJsoSDQPr2qV8yup5EmVwru7+XltFIsVxlOoqFwIxqR2qv870JjTJWZIRScqQa4GHDteeDanp5a4g+SK8mKdB5tOV0SzNCQlyBugGPnZqWuthpg602G4PpmTaSzDjvwjVlBStv9bZrF3m44DRHdLJcv3QKBgFWq98/ibfoK2o6HLZkOgtXouanI3J8noKj12GhyAO/uwbb/Scw26Aa1T6rfqQp6IFSCfNK8jkNVj2iKclgk8H/5ZeS1lYUEhvhWtcya+80JX17DnUefOXw8oJo0uBiYW4VoHOqmOVdErrA6ydCvAotxYK1JFt1M1yLzpxm3i3qjAoGAQkkglFQH/v4iKfxaqTHUpqHunWWu6GJSD0kKDUKlPLS4meOr3+s36otZh1qBs0eQNgxiip0VsBcooEPjytjIlmZ7qLLd+sGXJqA75r3XzpdOiHyltgtob6AdeQAU5huZEARQdZV1ZifO0O6oM1tJmYVCy4i3wGIoa4fu6kJ0ocECgYEA5C3ifFTOzJ/bhzkYJqvKOJ0oVydC/ICd6/tLYXyCO7KPeY34vJJxkKG7uMr8ymR01WA6ZuKrSTIoiqEYaQeUIb41odGQrJQ0DkxwiiMo9Hb1bvbwItpSk82o5Iq9XReBJYcNtMyLZpmrtEmHq6pTsVoDqOnjY28/MpoZ/X+Nl5Q=",
                    "json",
                    "utf-8",
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAySsyksnRqn+6FNrvrMlShX8d4gOvD8HSgptFdo6gmZCDSbI2C2a05b8vdaHT8PMD1eV0qIwEmtUlaGrI8xcZHD63/M0qNBWSJy0kCb6pfyuChB9zA29QGvYv5bs6VCG25HuZTU9lTxGvWa03r58RT8sBVye31CfoEQxT3lJ8nyqqWuY6BiUIpqYjvF7jQBLehO2cJAsb7i2WG8DSQPQXDcUUYZrAudYZcFV3tIQ1PxmK1oSwSJugfsAzNn6tkXWZYPXsXKqkpXmNr+EGcU5PA2aUHMcxhjdbfSfaEatdcsTnTyAOjY/YrlaPc0wkjEG6b5jCaw1ZtvHRjJrnMB2FpQIDAQAB",
                    "RSA2"); //获得初始化的AlipayClient
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
            alipayRequest.setReturnUrl("http://localhost:9100/order/success");
            alipayRequest.setNotifyUrl("http://localhost:9100/order/notify");//在公共参数中设置回跳和通知地址

            //订单编号 金额 对账
            alipayRequest.setBizContent("{" +
                    "    \"out_trade_no\":\""+orderNo+"\"," +
                    "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "    \"total_amount\":8888.88," +
                    "    \"subject\":\"Iphonex 256G\"," +
                    "    \"body\":\"Iphonex 256G\"," +
                    "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"" +
                    "  }");//填充业务参数
            String form="";
            try {
                form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            httpResponse.setContentType("text/html;charset=utf-8");
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        }

        @RequestMapping("success")
        public String success(){
            System.out.println("官方说明不代表支付成功！！！！！！！");
            return "success";
        }

        @RequestMapping("notify")
        public String notifyResult(){
            System.out.println("异步回调代表支付成功！！！！！！！");
            return "success";
        }

        @RequestMapping("hello")
        public String hello(){
            return "hello";
        }



}
