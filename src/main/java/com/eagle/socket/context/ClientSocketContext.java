package com.eagle.socket.context;

import com.eagle.socket.dao.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SukangLee
 * @version 1.0
 * @date 2023/10/12 22:36
 * @description 套接字客户端上下文
 */
public class ClientSocketContext {
    private static List<Client> clientSocketList = new ArrayList<>();

    public static void addClient(Client clientSocket) {
        clientSocketList.add(clientSocket);
    }

    public static void shutdown() {
        for (Client client : clientSocketList) {
            client.stop();
        }
    }
}
