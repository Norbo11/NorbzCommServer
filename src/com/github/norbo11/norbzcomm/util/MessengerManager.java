package com.github.norbo11.norbzcomm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTextPane;

import com.github.norbo11.norbzcomm.main.Main;
import com.github.norbo11.norbzcomm.settings.chatrooms.ChatRoomsCategory;

public class MessengerManager {
    private static ArrayList<Chat> chats = new ArrayList<Chat>();
    private static ArrayList<User> connectedUsers = new ArrayList<User>();
    private static ListeningThread listeningThread;

    public static ListeningThread getListeningThread() {
        return listeningThread;
    }

    public static void setListeningThread(ListeningThread listeningThread) {
        MessengerManager.listeningThread = listeningThread;
    }

    public static void setUserSockets(HashMap<User, Socket> userSockets) {
        MessengerManager.userSockets = userSockets;
    }

    private static HashMap<User, Socket> userSockets = new HashMap<User, Socket>();
    private static HashMap<Socket, UserListeningThread> userThreads = new HashMap<Socket, UserListeningThread>();
    private static HashMap<Socket, ObjectOutputStream> userOut = new HashMap<Socket, ObjectOutputStream>();
    private static HashMap<Socket, ObjectInputStream> userIn = new HashMap<Socket, ObjectInputStream>();

    public static void setChats(ArrayList<Chat> chats) {
        MessengerManager.chats = chats;
    }

    public static void setConnectedUsers(ArrayList<User> connectedUsers) {
        MessengerManager.connectedUsers = connectedUsers;
    }


    public static HashMap<Socket, ObjectOutputStream> getUserOut() {
        return userOut;
    }

    public static HashMap<Socket, ObjectInputStream> getUserIn() {
        return userIn;
    }

    public static void setUserOut(HashMap<Socket, ObjectOutputStream> userOut) {
        MessengerManager.userOut = userOut;
    }

    public static void setUserIn(HashMap<Socket, ObjectInputStream> userIn) {
        MessengerManager.userIn = userIn;
    }

    public static void addChat(Chat chat) {
        chats.add(chat);
        ChatRoomsCategory.updateList();
    }

    public static Chat findChat(Socket socket) {
        User user = findUser(socket);
        
        for (Chat chat : chats) {
            for (User chatUser : chat.getUsers())
                if (user == chatUser) return chat;
        }
        return null;
    }
    
    public static Chat findChat(String name) {
        for (Chat chat : chats) {
            if (chat.getName().equals(name)) return chat;
        }
        return null;
    }

    public static User findUser(Socket socket) {
        for (Entry<User, Socket> entry : userSockets.entrySet()) {
            if (entry.getValue() == socket) return entry.getKey();
        }
        return null;
    }

    public static ArrayList<Chat> getChats() {
        return chats;
    }

    public static ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }

    public static Socket getUserSocket(User user) {
        for (Entry<User, Socket> entry : userSockets.entrySet()) {
            if (entry.getKey() == user) return entry.getValue();
        }
        return null;
    }

    public static HashMap<User, Socket> getUserSockets() {
        return userSockets;
    }

    public static HashMap<Socket, UserListeningThread> getUserThreads() {
        return userThreads;
    }

    public static void setUserThreads(HashMap<Socket, UserListeningThread> userThreads) {
        MessengerManager.userThreads = userThreads;
    }

    public static void join(User user, Chat chat) {
        leave(user);
        chat.getUsers().add(user);
        CommProtocol.sendChat(user, chat);
        CommProtocol.sendServerMessage(chat, user.getUsername() + " has joined the chat.");
    }

    private static void leave(User user) {
        for (Chat chat : chats) {
            chat.getUsers().remove(user);
        }
    }

    public static void removeChat(Chat chat) {
        chats.remove(chat);
        ChatRoomsCategory.updateList();
    }

    public static void reset() {
        chats = new ArrayList<Chat>();
    }

    public static void sendMessage(Chat chat, Message message) {
        CommProtocol.sendChatMessage(chat, message);
    }

    public static void connect(Socket socket, User user) {
        userSockets.put(user, socket);
    }
    
    public static void log(String string) {
        JTextPane textArea = Main.getTextArea();
        textArea.setText(textArea.getText() + string + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
