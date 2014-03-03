package com.github.norbo11.norbzcomm.settings.chatrooms;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.github.norbo11.norbzcomm.util.Chat;
import com.github.norbo11.norbzcomm.util.MessengerManager;

public class ChatRoomsCategory extends JPanel {

    public ChatRoomsCategory() {
        setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(10, 133, 133, 20);
        add(nameField);
        nameField.setColumns(10);

        // Feed chatlist the list in frame through a new model
        listModel = new DefaultListModel<Chat>();
        for (Chat chat : MessengerManager.getChats())
            listModel.addElement(chat);

        chatList = new JList<Chat>(listModel);
        chatList.setVisibleRowCount(9);
        chatList.setLayoutOrientation(JList.VERTICAL_WRAP);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.setBounds(10, 166, 430, 123);
        add(chatList);

        ChatRoomsCategoryListener listener = new ChatRoomsCategoryListener();
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(listener);
        btnEdit.setBounds(351, 132, 89, 23);
        add(btnEdit);

        btnRemove = new JButton("Remove");
        btnRemove.addActionListener(listener);
        btnRemove.setBounds(252, 132, 89, 23);
        add(btnRemove);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(listener);
        btnAdd.setBounds(153, 132, 89, 23);
        add(btnAdd);

        lblEnterTheChat = new JLabel("Enter the chat rooms to be used for the server.");
        lblEnterTheChat.setBounds(10, 11, 430, 14);
        add(lblEnterTheChat);

        lblChatName = new JLabel("Chat Name:");
        lblChatName.setLabelFor(nameField);
        lblChatName.setBounds(10, 113, 113, 14);
        add(lblChatName);
    }

    private static final long serialVersionUID = 1L;
    private static JTextField nameField;
    private static JList<Chat> chatList;

    private static DefaultListModel<Chat> listModel = new DefaultListModel<Chat>();

    private static EditChatFrame editChatFrame;

    private static JButton btnEdit;

    private static JButton btnRemove;

    private static JButton btnAdd;
    private JLabel lblEnterTheChat;
    private JLabel lblChatName;

    public static JButton getBtnAdd() {
        return btnAdd;
    }

    public static JButton getBtnEdit() {
        return btnEdit;
    }

    public static JButton getBtnRemove() {
        return btnRemove;
    }

    public static JList<Chat> getChatList() {
        return chatList;
    }

    public static EditChatFrame getEditChatFrame() {
        return editChatFrame;
    }

    public static DefaultListModel<Chat> getListModel() {
        return listModel;
    }

    public static JTextField getNameField() {
        return nameField;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static void setEditChatFrame(EditChatFrame editChatFrame) {
        ChatRoomsCategory.editChatFrame = editChatFrame;
    }

    public static void updateList() {
        DefaultListModel<Chat> listModel = ChatRoomsCategory.getListModel();
        listModel.clear();
        for (Chat chat : MessengerManager.getChats()) {
            listModel.addElement(chat);
        }
    }

    public JLabel getLblChatName() {
        return lblChatName;
    }

    public JLabel getLblEnterTheChat() {
        return lblEnterTheChat;
    }
}
