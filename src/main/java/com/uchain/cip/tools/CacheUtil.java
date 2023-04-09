package com.uchain.cip.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 缓存工具类
 * */

@Component
@Slf4j
public class CacheUtil {
    @Autowired
    EmailUtil emailUtil;

    /**
     * 发送验证码，存入缓存，五分钟有效
     * */
    @CachePut(value = "verifyCode", key = "#email")
    public String setVerifyCode(String email) {
        //生成四位数随机验证码
        String sendVerifyCode = String.format("%04d", new Random().nextInt(9999 - 1000 + 1) + 1000);
        String text = "[智慧校园互助平台]\n您正在注册智慧校园互助平台账户\n\t验证码：" + sendVerifyCode + "\n若非本人操作，请忽略此条信息~";
        log.info("发送验证码-- " + sendVerifyCode + " --到邮箱-- " + email);
        //发送验证码
        emailUtil.sendSimpleMailMessage(email, "智慧校园互助平台", text);

        return sendVerifyCode;
    }

    /**
     * 从缓存中获取验证码，若无对应缓存数据，则返回null
     * */
    @Cacheable(value = "verifyCode", key = "#email")
    public String getVerifyCode(String email) {
        return null;
    }
}
