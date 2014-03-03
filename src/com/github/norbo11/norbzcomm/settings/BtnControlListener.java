package com.github.norbo11.norbzcomm.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.norbo11.norbzcomm.util.ServerConfig;

public class BtnControlListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == SettingsFrame.getBtnOk()) {
            ServerConfig.saveConfig();
        }

        SettingsFrame.getFrame().setVisible(false);
    }
}
