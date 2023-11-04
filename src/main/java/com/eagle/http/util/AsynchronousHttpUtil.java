package com.eagle.http.util;

import com.eagle.http.dao.Http;
import com.eagle.thread.EagleThreadPoolService;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 23:24
 * @description 异步HTTP工具
 */
public class AsynchronousHttpUtil implements Http {
    private DefaultHttpUtil util;

    public AsynchronousHttpUtil() {
        util = new DefaultHttpUtil();
    }

    @Override
    public String get(String url, Map<String, Object> params) throws ExecutionException, InterruptedException {
        final String val = null;
        EagleThreadPoolService.getInstance().submit( () -> {
            util.get(url, params);
        });
        return val;
    }

    @Override
    public String post(String url, Map<String, Object> params) {
        final String val = null;
        EagleThreadPoolService.getInstance().submit( () -> {
            util.post(url, params);
        });
        return val;
    }
}
