package com.eagle.http.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.eagle.http.dao.Http;

import java.util.Map;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 23:19
 * @description 默认HTTP实现
 */
public class DefaultHttpUtil implements Http {
    @Override
    public String get(String url, Map<String, Object> params) {
        String body = HttpUtil.get(url, params);
        return body;
    }

    @Override
    public String post(String url, Map<String, Object> params) {
        String body = HttpRequest.post(url).body(new JSONObject(params).toJSONString())
                .execute().body();
                //HttpUtil.post(url, params, 5000);
        return body;
    }
}
