package com.github.norbo11.norbzcomm.settings.chatrooms;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import com.github.norbo11.norbzcomm.util.Chat;

public class EditChatFrame {
    public EditChatFrame(Chat chat) {
        editedChat = chat;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 471, 298);
        frame.getContentPane().setLayout(null);
        initialize();
        frame.setVisible(true);
    }

    private static JFrame frame;
    private static JList<String> opList;
    public static JList<String> getOpList() {
        return opList;
    }

    public static JButton getBtnCancel() {
        return btnCancel;
    }

    private static DefaultListModel<String> opListModel;
    private static JTextField chatName;
    private static JTextField chatOwner;
    private static JTextField opName;
    private static JButton btnOk;
    private static JButton btnAdd;
    private static JButton btnRemove;
    private static JButton btnCancel;

    public static JButton getBtnRemove() {
        return btnRemove;
    }

    public static void setBtnRemove(JButton btnRemove) {
        EditChatFrame.btnRemove = btnRemove;
    }

    public static DefaultListModel<String> getOpListModel() {
        return opListModel;
    }

    public static JButton getBtnOk() {
        return btnOk;
    }

    private static Chat editedChat;

    public static JTextField getChatName() {
        return chatName;
    }

    public static JTextField getChatOwner() {
        return chatOwner;
    }

    public static Chat getEditedChat() {
        return editedChat;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static JTextField getOpName() {
        return opName;
    }

    private void initialize() {
        chatName = new JTextField(editedChat.getName());
        chatName.setBounds(113, 11, 125, 20);
        frame.getContentPane().add(chatName);

        chatOwner = new JTextField(editedChat.getOwner());
        chatOwner.setBounds(113, 38, 125, 20);
        frame.getContentPane().add(chatOwner);

        opName = new JTextField();
        opName.setColumns(10);
        opName.setBounds(113, 66, 125, 20);
        frame.getContentPane().add(opName);

        // Feed operators from chat to list through a new model
        opListModel = new DefaultListModel<String>();
        for (String op : editedChat.getOperators())
            opListModel.addElement(op);

        opList = new JList<String>(opListModel);
        opList.setLayoutOrientation(JList.VERTICAL_WRAP);
        opList.setBounds(10, 99, 435, 116);
        frame.getContentPane().add(opList);

        JLabel lblChatName = new JLabel("Chat Name:");
        lblChatName.setLabelFor(chatName);
        lblChatName.setBounds(10, 14, 93, 14);
        frame.getContentPane().add(lblChatName);

        JLabel lblOwner = new JLabel("Owner:");
        lblOwner.setLabelFor(chatOwner);
        lblOwner.setBounds(10, 41, 93, 14);
        frame.getContentPane().add(lblOwner);

        JLabel lblOperators = new JLabel("Operators:");
        lblOperators.setLabelFor(opName);
        lblOperators.setBounds(10, 69, 93, 14);
        frame.getContentPane().add(lblOperators);

        EditChatListener listener = new EditChatListener();
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(listener);
        btnAdd.setBounds(248, 65, 89, 23);
        frame.getContentPane().add(btnAdd);

        btnRemove = new JButton("Remove");
        btnRemove.addActionListener(listener);
        btnRemove.setBounds(347, 65, 89, 23);
        frame.getContentPane().add(btnRemove);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(listener);
        btnCancel.setBounds(347, 226, 89, 23);
        frame.getContentPane().add(btnCancel);

        btnOk = new JButton("OK");
        btnOk.addActionListener(listener);
        btnOk.setBounds(248, 226, 89, 23);
        frame.getContentPane().add(btnOk);
    }

    public static JButton getBtnAdd() {
        return btnAdd;
    }

    public static void update() {
        DefaultListModel<String> listModel = EditChatFrame.getOpListModel();
        listModel.clear();
        for (String op : EditChatFrame.getEditedChat().getOperators()) {
            listModel.addElement(op);
        }
    }

}
