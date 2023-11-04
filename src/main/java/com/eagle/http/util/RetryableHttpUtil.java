package com.eagle.http.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eagle.http.config.HttpConfigUtil;
import com.eagle.http.dao.Http;
import com.eagle.thread.EagleThreadPoolService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 23:39
 * @description 带有错误重传的异步HTTP工具
 */
@Slf4j
public class RetryableHttpUtil implements Http {
    private final DefaultHttpUtil util;

    public RetryableHttpUtil() {
        util = new DefaultHttpUtil();
    }

    @Override
    @Deprecated
    public String get(String url, Map<String, Object> params) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public String post(String url, Map<String, Object> params) {
        EagleThreadPoolService.getInstance().submit(new PostTask(HttpConfigUtil.BackendURL(url), params));
        return null;
    }

    private class PostTask implements Runnable {
        private final int MAX_RETRY_TIMES = 10;
        private final String url;
        private final Map<String, Object> params;
        private int times;

        public PostTask(String url, Map<String, Object> params) {
            this.url = url;
            this.params = params;
            this.times = 0;
        }

        @Override
        public void run() {
            try {
                String body = util.post(url, params);
                JSONObject object = JSON.parseObject(body);
                log.info("{}", object);
                if (object == null || 200 != Integer.parseInt(String.valueOf(object.get("code")))) {
                    retry();
                }
            } catch (Exception e) {
                log.info("post错误:{}", e.getMessage());
                retry();
            }
        }

        private void retry() {
            times += 1;
            if (times > MAX_RETRY_TIMES) {
                return;
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.info("post请求睡眠间隔被打断: {}", e.getMessage());
            }
            EagleThreadPoolService.getInstance().submit(this);
        }
    }
}
