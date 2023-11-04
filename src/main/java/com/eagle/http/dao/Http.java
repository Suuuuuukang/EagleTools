package com.eagle.http.dao;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 23:17
 * @description
 */
public interface Http {
    String get(String url, Map<String, Object> params) throws ExecutionException, InterruptedException;

    String post(String url, Map<String, Object> params);
}