package com.eagle.socket.dao;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 21:42
 * @description
 */
public enum SocketState {
    Ready(0),
    Running(1),
    Closed(2);

    SocketState(int state) {
        this.state = state;
    }
    private int state;

    public int getState() {
        return state;
    }
}
