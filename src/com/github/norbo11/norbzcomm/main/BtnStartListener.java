package com.github.norbo11.norbzcomm.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.github.norbo11.norbzcomm.util.ListeningThread;
import com.github.norbo11.norbzcomm.util.MessengerManager;
import com.github.norbo11.norbzcomm.util.NumberHelper;

public class BtnStartListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (MessengerManager.getListeningThread() == null) {
            try {
                MessengerManager.log("Started listening on port " + NumberHelper.toInt(Main.getPortField().getText()));
                MessengerManager.setListeningThread(new ListeningThread());
                MessengerManager.getListeningThread().start();
                Main.getBtnStart().setText("Stop!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(Main.getFrame(), "Invalid port number!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                MessengerManager.log("Server stopped.");
                MessengerManager.getListeningThread().getServerSocket().close();
                MessengerManager.setListeningThread(null);
                Main.getBtnStart().setText("Start!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
