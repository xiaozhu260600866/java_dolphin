package com.xxx.server.utils.wapay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

public class WxOrder {
      public static Map order(Map<String,String> orderParams){
          JSAPIConfig config = new JSAPIConfig();
          Map<String, String> resault = new HashMap<String, String>();
          WXPay wxpay = new WXPay(config);
          try {
              Map<String, String> map = wxpay.unifiedOrder(orderParams);
              Long timeStamp= System.currentTimeMillis();
              String prepay_id="prepay_id="+map.get("prepay_id");
              String nonceStr = WXPayUtil.generateNonceStr();
              resault.put("appId",config.getAppID());
              resault.put("timeStamp",timeStamp.toString());
              resault.put("nonceStr",nonceStr);
              resault.put("package",prepay_id);
              resault.put("signType","MD5");
              String sign=WXPayUtil.generateSignature(resault, config.getKey());
              resault.put("paySign",sign);
              resault.put("code","0");
          } catch (Exception e) {
              e.printStackTrace();
          }
          return resault;

      }
}
