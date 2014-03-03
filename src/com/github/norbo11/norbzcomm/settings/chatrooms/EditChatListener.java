package com.github.norbo11.norbzcomm.settings.chatrooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import com.github.norbo11.norbzcomm.util.Chat;

public class EditChatListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        Chat chat = EditChatFrame.getEditedChat();
        
        if (event.getSource() == EditChatFrame.getBtnCancel() || event.getSource() == EditChatFrame.getBtnOk()) {
            if (event.getSource() == EditChatFrame.getBtnOk()) {
                chat.setName(EditChatFrame.getChatName().getText());
                chat.setOwner(EditChatFrame.getChatOwner().getText());
                
                chat.getOperators().clear();
                DefaultListModel<String> opListModel = EditChatFrame.getOpListModel();
                
                String[] ops = new String[opListModel.size()];
                opListModel.copyInto(ops);
                for (String op : ops)
                {  
                    chat.getOperators().add(op);
                }
            }
            
            EditChatFrame.getFrame().setVisible(false);
        }
        
        if (event.getSource() == EditChatFrame.getBtnAdd())
        {
           EditChatFrame.getOpListModel().addElement(EditChatFrame.getOpName().getText());
           EditChatFrame.getOpName().setText("");
        }
        
        if (event.getSource() == EditChatFrame.getBtnRemove())
        {
            EditChatFrame.getOpListModel().removeElement(EditChatFrame.getOpList().getSelectedValue());
        }
    }
    
}
