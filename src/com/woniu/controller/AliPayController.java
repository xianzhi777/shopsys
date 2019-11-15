package com.woniu.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/pay")
public class AliPayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/ali")
    public void alipay(HttpServletResponse resp) throws IOException {
        String APP_ID = "2016101100663063";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIoM4NNt9KLT0Tq+ghqg/lpHZWtBKyX4wMTv0aV0yBI0R5wtdV51kunK4c8+hAqJ6VLini/nTqhSp3D9AY2j5JX9xnKRjEzKrVBPG6L5TwBk3vfKHEVX4iVojw6gxbxd7tC+xUxwPnE2fWgTC+6g7mADevKSyKK3zOB0FmhV5xTZvquKSxyNxtBEur6LHPAFjSaSihMchIxGny3xB/mwfqnKh0Sau89te52BJ7sl/LQtUcM18xCQct/XI/78wz0MreP38/Z1Bu9nvtJrRW/OYn/gAsuY0ikDhHkrrnzCcXiPXSqzjqolYVWlRYbNXYIIL0iQ9kJE0VjcasNb13FFmpAgMBAAECggEAfGN6dQlqDMxW+NvfgRalVnt+yb0z8HWQuXwIbrzgCAolw30iMzFnPkqjMfTVlFhv2H2o4OKtav5GBRO+b4HyhzbhIIKH7FQxi7LuqIW9YTE8LzcWx/rV1UlXb46GJd9l8AaU6TIvWWePd/kyef36BmhYSf6AcGLbQ2/TmRT/QMMEOry3RAkDp0K9R7A7fAH/eEu9bUZngZBim0Evshdj4VMD1264dvBxp7+vyj4eeQs0zE92lqRVDYaHbd8pDRafwczUJsr3mFTY9+NwYK5DUwr5BoitqxWoml/0iVJ30i2bzgxB3FkMuRgzcD3bEMSJ9VJ05rSPNauTDp7OrVMYIQKBgQDIlg7fYYlJsU9vn2C4C+ePH27npl0c7FL39SqTrkYXy22ATlTlx5ruJ3qKGiSMUTm8UCChMdruQgRiKY7cer+35fv0GY7kbZohkYg1pigySUi8g9b8u7pCtLY64dLAgjm+NRlGZMEBzWl6Zz3x7u/BbMQmsimnnEVw/ptMhZUSfQKBgQCuX3rZam/Ie+vlfH/ciKloTWgM2pVsXgxb8p5/q3M862tOK1Eh0hHURcpaBrkzUpbvP+H0au3TgoWDo4kF+zGtvJjQbLhycbPf5wRyIKPOtvEklvOoISSZCyPmR2I2xnBOWSt9SQ2uKt009/K2x7wD+6WG5DTi/Z3vxtdEXSx/nQKBgBXTVhzyRPjyxRz1L5X3HaQSHIQyMvGBuWlnkNFu5guSxABxBYXXGUGOpCPAwndOI0UdRt+jSBoirs1rjjLpXFELQkYGPekXD+1VI4gK41bNaI+ynML1Pkgh165CgicWRGgw6OYi8mlay35ZG4FmqUnXpuQ7AJNPSrMH0SgbZOQVAoGAQonMpwomfapL6dYING8xtctT2RPP0PpSXQPH14/k+WocGEgPPYibpRS8kn3eTedGESWfY1UqS3uS1xigQNgJrPI6nc3e1EzvBpFw8Nvjpg3JFt1DPPTosj2+BTFYGO7ADCWaosQtpDhXEM9MntK4vAxarY6HX9bHXafSnROw10ECgYEAkXpaw8DNV+bPuxyCXZNq55ocyBK3fEO3JUNZLtbq82yZW5PJTt0aaeRNlSBD5jmW/5BO/D5Ig+ju+DAdPS0UlKToiTOt/NRo3FT+ScUXJpiHKpEBGjTcLeFsCQY1VYj9zp0st8XzTQosJ4yhHwn5bp5O0jardeYNIG55NCP4Xxg=";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiKDODTbfSi09E6voIaoP5aR2VrQSsl+MDE79GldMgSNEecLXVedZLpyuHPPoQKielS4p4v506oUqdw/QGNo+SV/cZykYxMyq1QTxui+U8AZN73yhxFV+IlaI8OoMW8Xe7QvsVMcD5xNn1oEwvuoO5gA3ryksiit8zgdBZoVecU2b6rikscjcbQRLq+ixzwBY0mkooTHISMRp8t8Qf5sH6pyodEmrvPbXudgSe7Jfy0LVHDNfMQkHLf1yP+/MM9DK3j9/P2dQbvZ77Sa0VvzmJ/4ALLmNIpA4R5K658wnF4j10qs46qJWFVpUWGzV2CCC9IkPZCRNFY3GrDW9dxRZqQIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "app_id", "your private_key", "json", "UTF-8", "alipay_public_key", "RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("");
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(form);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @RequestMapping("/now")
    @ResponseBody
    public int paynow(int aid) {
        if (orderService.payorderfin(aid)) {
            return 1;
        }
        return -1;
    }
}
