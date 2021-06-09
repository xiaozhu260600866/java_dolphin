package com.xxx.server.utils.wapay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

public class JSAPIConfig implements WXPayConfig {

    @Override
    public String getAppID() {
        return "wx13eb6f69c3407043";
    }

    @Override
    public String getMchID() {
        return "1321202701";
    }

    @Override
    public String getKey() {
        return "u3jisSJudrN7ZjkH2EweF6HpWbviLmmZ";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}