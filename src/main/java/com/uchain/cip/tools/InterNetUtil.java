package com.uchain.cip.tools;

import java.net.*;

/**
 * 互联网工具类
 * */
public class InterNetUtil {
    /**
     * 解析域名对应的IP地址
     * */
    public static String domainNameToIp(String domainName) {
        InetAddress ipAddress;
        String hostAddress = "unknown";
        try {
            ipAddress = InetAddress.getByName(domainName);
            hostAddress = ipAddress.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Unable to resolve IP address for " + domainName);
        }

        return hostAddress;
    }
}
