package com.github.norbo11.norbzcomm.settings.chatrooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextField;

import com.github.norbo11.norbzcomm.settings.SettingsFrame;
import com.github.norbo11.norbzcomm.util.Chat;
import com.github.norbo11.norbzcomm.util.MessengerManager;
import com.github.norbo11.norbzcomm.util.UIHelper;

public class ChatRoomsCategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == ChatRoomsCategory.getBtnAdd()) {
            JTextField nameField = ChatRoomsCategory.getNameField();
            String name = nameField.getText();

            if (name.equalsIgnoreCase("")) {
                UIHelper.showErrorDialog(SettingsFrame.getFrame(), "You must enter a valid chat name!");
                return;
            }

            nameField.setText("");
            MessengerManager.addChat(new Chat(name));
        }

        if (event.getSource() == ChatRoomsCategory.getBtnEdit()) {
            Chat chat = ChatRoomsCategory.getChatList().getSelectedValue();

            if (chat != null) ChatRoomsCategory.setEditChatFrame(new EditChatFrame(chat));
        }

        if (event.getSource() == ChatRoomsCategory.getBtnRemove()) {
            JList<Chat> list = ChatRoomsCategory.getChatList();
            MessengerManager.removeChat(list.getSelectedValue());
        }

    }

}
