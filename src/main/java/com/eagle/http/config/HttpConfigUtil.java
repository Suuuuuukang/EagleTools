package com.eagle.http.config;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/16 17:49
 * @description
 */
public class HttpConfigUtil {
    private static String OCC_BACKEND_URL = "127.0.0.1:9600";

    // TODO: 修改请求url的动态配置功能
    public static String getOccBackendUrl() {
        return OCC_BACKEND_URL;
    }

    public static void setOccBackendUrl(String occBackendUrl) {
        OCC_BACKEND_URL = occBackendUrl;
    }

    /**
     * 添加URL前缀
     * @param url api-URL
     * @return URL
     */
    public static String BackendURL(String url) {
        return OCC_BACKEND_URL + url;
    }
}