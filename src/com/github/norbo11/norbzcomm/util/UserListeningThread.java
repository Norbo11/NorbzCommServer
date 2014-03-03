package com.github.norbo11.norbzcomm.util;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class UserListeningThread extends Thread {
    public UserListeningThread(Socket socket) {
        this.socket = socket;
    }

    Socket socket = null;
    @Override
    public void run() {
        while (true)
        {   
            try {
                Object object = MessengerManager.getUserIn().get(socket).readObject();
                if (object != null)
                {
                    Packet packet = (Packet) object;
                    MessengerManager.log(socket.getInetAddress() + " ---> " + packet.getCommand() + " - " + packet.getPayload());
                    CommProtocol.process(socket, packet);
                }
            } catch (EOFException e)
            {
                break;
            } catch (SocketException e)
            {
                MessengerManager.log("Connection lost with " + socket.getInetAddress());
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
