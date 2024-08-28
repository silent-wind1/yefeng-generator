package com.yefeng.web.config;


import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 叶枫
 * @Date: 2024/07/13/15:45
 * @Description:
 */
@Configuration
public class WeChatCodeConfig {
    @Value("${wx.open.appid}")
    private String appId;

    @Value("${wx.open.appsecret}")
    private String appSecret;

    /**
     * 定义WxMpService bean
     */
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(appId);
        config.setSecret(appSecret);
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }
}
