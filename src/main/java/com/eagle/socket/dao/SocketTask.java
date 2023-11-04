package com.eagle.socket.dao;

import java.io.IOException;
import java.net.Socket;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 22:27
 * @description 套接字核心任务类
 */
public interface SocketTask{
    /**
     * 一次套接字任务，如读取一次数据或发送一次数据
     * @param socket 套接字client
     */
    void run(Socket socket) throws IOException, InterruptedException;
}
