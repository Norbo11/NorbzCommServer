package com.github.norbo11.norbzcomm.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.github.norbo11.norbzcomm.main.Main;

public class ListeningThread extends Thread {
    ServerSocket serverSocket = null;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(NumberHelper.toInt(Main.getPortField().getText()));
            while (MessengerManager.getListeningThread() != null) {
                try {
                    clientSocket = serverSocket.accept();
                    MessengerManager.log("Connected to " + clientSocket.getInetAddress());

                    MessengerManager.getUserIn().put(clientSocket, new ObjectInputStream(clientSocket.getInputStream()));
                    MessengerManager.getUserOut().put(clientSocket, new ObjectOutputStream(clientSocket.getOutputStream()));
                    MessengerManager.getUserOut().get(clientSocket).flush();
                    
                    UserListeningThread thread = new UserListeningThread(clientSocket);
                    MessengerManager.getUserThreads().put(clientSocket, thread);
                    MessengerManager.getUserThreads().get(clientSocket).start();
                } catch (SocketException e) {
                    // Called if the socket was closed while accepting
                    break;
                }
            }

            serverSocket.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
