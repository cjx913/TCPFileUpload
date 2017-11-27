package com.cjx913.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8878);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new ServerThread(socket)).start();
        }
    }
}
