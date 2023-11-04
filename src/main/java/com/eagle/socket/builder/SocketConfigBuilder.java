package com.eagle.socket.builder;

import com.eagle.socket.dao.SocketConfig;
import com.eagle.socket.dao.SocketTask;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 21:51
 * @description 套接字配置创建
 */
public class SocketConfigBuilder {
    private SocketConfig config;

    private SocketConfigBuilder() {
        this.config = new SocketConfig();
    }

    public static SocketConfigBuilder create() {
        return new SocketConfigBuilder();
    }

    public SocketConfigBuilder name(String name) {
        config.setSocketName(name);
        return this;
    }

    public SocketConfigBuilder task(SocketTask task) {
        config.setTask(task);
        return this;
    }

    public SocketConfigBuilder addr(String addr) {
        config.setAddr(addr);
        return this;
    }

    public SocketConfigBuilder port(int port) {
        config.setPort(port);
        return this;
    }

    public SocketConfig build() {
        return this.config;
    }
}
