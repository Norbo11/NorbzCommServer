package com.github.norbo11.norbzcomm.util;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class CommProtocol {
    public static void process(Socket socket, Packet packet) {
        Object payload = packet.getPayload();
        String line = (String) packet.getCommand();
        String[] args = line.split(" ");
        User user = MessengerManager.findUser(socket);

        switch (args[0]) {
            case "REQUEST":
                switch (args[1]) {
                    case "CHATLIST":
                        sendChatList(user);
                        break;
                }
                break;
            case "JOIN":
                MessengerManager.join(user, MessengerManager.findChat(args[1]));
                break;
            case "MESSAGE":
                MessengerManager.sendMessage(MessengerManager.findChat(socket), (Message)payload);
                break;
            case "CONNECT":
                MessengerManager.connect(socket, (User) payload);
                break;
        }
        return;
    }

    public static void sendChat(User user, Chat chat) {
        sendSerialized(MessengerManager.getUserSocket(user), "CHAT", chat);
    }

    public static void sendChatList(User user) {
        ArrayList<String> chatNames = new ArrayList<String>();

        for (Chat chat : MessengerManager.getChats()) {
            chatNames.add(chat.getName());
        }

        sendSerialized(MessengerManager.getUserSocket(user), "CHATLIST", chatNames);
    }
    
    public static void sendSerialized(Socket socket, String command, Object payload) {
        try {
            MessengerManager.log(command + " - " + payload + " ---> " + socket.getInetAddress());
            
            MessengerManager.getUserOut().get(socket).writeObject(new Packet(command, payload));
            MessengerManager.getUserOut().get(socket).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendChatMessage(Chat chat, Message message)
    {
        for (User chatUser : chat.getUsers())
        {
            sendSerialized(MessengerManager.getUserSocket(chatUser), "MESSAGE", message);
        }
    }

    public static void sendServerMessage(Chat chat, String string) {
        sendChatMessage(chat, new Message(new Date().getTime(), string));
    }

}
