package com.xxx.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.xxx.server.utils.wapay.JSAPIConfig;
import com.xxx.server.mapper.OrderMapper;
import com.xxx.server.pojo.Poster;
import com.xxx.server.service.IPosterService;
import com.xxx.server.utils.Utils;
import com.xxx.server.utils.wapay.WxOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeControoler {
    @Autowired
    private IPosterService posterService;
    @Autowired
    private OrderMapper orderMapper;
    @ApiOperation("首页")
    @GetMapping("index")
    public Map getIndex(@RequestParam(required = false) Map params) throws Exception {

        //wxpayStart
//        Map<String, String> data = new HashMap<String, String>();
//        String orderId = System.currentTimeMillis() + "js1" ;
//        data.put("body", "支付信息");//支付信息
//        data.put("out_trade_no", orderId); //订单号
//        data.put("device_info", "WEB");//设备信息
//        data.put("fee_type", "CNY");//币种
//        data.put("openid", (String) params.get("openid"));//币种
//        data.put("total_fee", "1");//充值金额
//        data.put("spbill_create_ip", "127.0.0.1");//客户端IP
//        data.put("notify_url","127.0.0.0:1/api/notify");//异步回调通知地址
//        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
//        params.put("config",JSONObject.toJSONString(WxOrder.order(data)));
        //wxpayEnd
        List<Poster> silders = posterService.list(new QueryWrapper<Poster>().eq("type", 1));
        List<Poster> location = posterService.list(new QueryWrapper<Poster>().eq("type", 2));
        List<Poster> lists = posterService.list(new QueryWrapper<Poster>().eq("type", 3));
        params.put("silders",silders);
        params.put("location",location);
        params.put("lists",lists);


        return params;
    }

    @ApiOperation("会员首页")
    @GetMapping("user")
    public Map getUser(@RequestParam(required = false) Map params){
        if(Utils.getUser() !=null){
            params.put("userId", Utils.getUser().getId());
            params.put("status", 0);
            params.put("status0Count", orderMapper.selectCount(params));

            params.put("status", 1);
            params.put("status1Count", orderMapper.selectCount(params));

            params.put("status", 2);
            params.put("status2Count", orderMapper.selectCount(params));

            params.put("user", Utils.getUser());

        }else{
            params.put("user", null);
            params.put("status0Count", null);
            params.put("status1Count", null);
            params.put("status2Count", null);
        }
        return params;
    }

}
