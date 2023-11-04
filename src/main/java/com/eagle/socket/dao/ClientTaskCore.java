package com.eagle.socket.dao;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 22:02
 * @description 套接字任务核心状态机
 */
@Slf4j
public class ClientTaskCore implements Runnable{
    private SocketState state;
    private final SocketConfig config;

    private Socket socket;

    public ClientTaskCore(SocketConfig config) {
        this.state = SocketState.Ready;
        this.config = config;
        this.socket = new Socket();
    }

    @Override
    public void run() {
        while (true) {
            if (state == SocketState.Ready || state == SocketState.Closed) {
                try {
                    tryConnect();
                } catch (InterruptedException e) {
                    break;
                }
            } else if (state == SocketState.Running) {
                try {
                    log.info("{}执行任务", config.getSocketName());
                    config.getTask().run(socket);
                } catch (Exception e) {
                    log.error("{}任务运行错误: {}", config.getSocketName(), e.getMessage());
                    state = SocketState.Closed;
                }
            }
            try {
                if (socket.isClosed()) {
                    state = SocketState.Closed;
                }
            } catch (NullPointerException e) {
                log.error("严重错误:{}", e.getMessage());
                socket = new Socket();
            }
            log.debug("{}", socket.isClosed());
        }
        log.info("线程{}停止", config.getSocketName());
    }

    private void tryConnect() throws InterruptedException {
        try {
            log.debug("尝试连接");
            socket.connect(new InetSocketAddress(config.getAddr(), config.getPort()), 5000);
            log.info("连接成功: {}", config);
            state = SocketState.Running;
        } catch (SocketException e) {
            log.error("{}连接错误: {}", config.getSocketName(), e.getMessage());
            if (e.getMessage().contains("closed")) {
                socket = new Socket();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException interruptedException) {
                    log.info("停止线程: {}", config.getSocketName());
                    throw interruptedException;
                }
                state = SocketState.Closed;
            } else if (e.getMessage().contains("connected")) {
                Thread.sleep(10000);
                state = SocketState.Running;
                //socket = new Socket();
            }
        } catch (IOException e) {
            log.error("Socket连接错误:{}", e.getMessage());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException interruptedException) {
                log.info("停止线程: {}", config.getSocketName());
                throw interruptedException;
            }
            state = SocketState.Closed;
        }
    }
}
