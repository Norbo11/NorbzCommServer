package com.github.norbo11.norbzcomm.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.github.norbo11.norbzcomm.settings.SettingsFrame;
import com.github.norbo11.norbzcomm.util.ServerConfig;

public class Main {

    private static JFrame frame;
    private static JTextField portField;
    private static JButton btnStart;
    private static SettingsFrame settingsFrame;
    private static ServerConfig serverConfig;
    private static JMenu mnFile;
    private static JMenu mnHelp;
    private static JMenuItem mntmSettings;
    private static JScrollPane scrollPane;
    private static JTextPane textArea;

    public static JButton getBtnStart() {
        return btnStart;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static JTextField getPortField() {
        return portField;
    }

    public static ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static SettingsFrame getSettingsFrame() {
        return settingsFrame;
    }

    public static JTextPane getTextArea() {
        return textArea;
    }

    private static void initialize() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnFile = new JMenu("Tools");
        menuBar.add(mnFile);

        mntmSettings = new JMenuItem("Settings");
        mntmSettings.addActionListener(new MntmSettingsListener());
        mnFile.add(mntmSettings);

        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        frame.getContentPane().setLayout(null);

        JLabel lblChooseAPort = new JLabel("Choose a port to start the server");
        lblChooseAPort.setBounds(10, 15, 350, 14);
        frame.getContentPane().add(lblChooseAPort);

        portField = new JTextField();
        portField.setBounds(10, 40, 350, 20);
        portField.setText("211");
        frame.getContentPane().add(portField);

        btnStart = new JButton("Start!");
        btnStart.setBounds(370, 11, 246, 49);
        btnStart.addActionListener(new BtnStartListener());
        frame.getContentPane().add(btnStart);
        
        textArea = new JTextPane();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 71, 606, 188);
        frame.getContentPane().add(scrollPane);
        
    }

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setBounds(100, 100, 642, 329);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
        ServerConfig.loadConfig();

        frame.setVisible(true);
    }

    public static void setSettingsFrame(SettingsFrame settingsFrame) {
        Main.settingsFrame = settingsFrame;
    }
}
