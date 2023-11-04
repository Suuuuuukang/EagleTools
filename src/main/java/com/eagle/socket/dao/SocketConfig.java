package com.eagle.socket.dao;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 21:47
 * @description 套接字配置
 */
public class SocketConfig {
    // 套接字名
    private String socketName;
    // 套接字单次任务
    private SocketTask task;
    // 目标地址
    private String addr;
    // 目标端口
    private int port;

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

    public SocketTask getTask() {
        return task;
    }

    public void setTask(SocketTask task) {
        this.task = task;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SocketConfig{" +
                "socketName='" + socketName + '\'' +
                ", task=" + task +
                ", addr='" + addr + '\'' +
                ", port=" + port +
                '}';
    }
}
