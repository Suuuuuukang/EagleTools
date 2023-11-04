package com.eagle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 23:26
 * @description
 */
public class EagleThreadPoolService {
    private ExecutorService service;

    private static final EagleThreadPoolService instance = new EagleThreadPoolService();

    public static EagleThreadPoolService getInstance() {
        return instance;
    }

    public EagleThreadPoolService() {
        service = new ScheduledThreadPoolExecutor(5);
    }

    public void submit(Runnable task) {
        service.submit(task);
    }
}
