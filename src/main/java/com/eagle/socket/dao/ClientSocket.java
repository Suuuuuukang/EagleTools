package com.eagle.socket.dao;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 21:45
 * @description 套接字客户端模版
 */
public class ClientSocket implements Client{
    private final SocketConfig config;

    private Thread thread;

    public ClientSocket(SocketConfig config) {
        this.config = config;
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void start() {
        this.thread = new Thread(new ClientTaskCore(config));
        this.thread.start();
    }
}
