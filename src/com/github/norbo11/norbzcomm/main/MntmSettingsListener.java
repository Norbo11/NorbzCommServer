package com.github.norbo11.norbzcomm.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.norbo11.norbzcomm.settings.SettingsFrame;
import com.github.norbo11.norbzcomm.util.ServerConfig;

public class MntmSettingsListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ServerConfig.loadConfig();
        Main.setSettingsFrame(new SettingsFrame());
    }
}
