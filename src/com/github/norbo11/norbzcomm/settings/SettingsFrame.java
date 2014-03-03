package com.github.norbo11.norbzcomm.settings;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.github.norbo11.norbzcomm.settings.chatrooms.ChatRoomsCategory;
import com.github.norbo11.norbzcomm.settings.export.ExportCategory;
import com.github.norbo11.norbzcomm.settings.general.GeneralCategory;

public class SettingsFrame {

    public SettingsFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 660, 435);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        initialize();
        frame.setVisible(true);
    }

    private static JFrame frame;
    private static JList<String> categoryList;
    private static DefaultListModel<String> categoryListModel;
    private static JPanel settingsContainer;
    private static JButton btnCancel;

    private static JButton btnOk;

    public static JButton getBtnCancel() {
        return btnCancel;
    }

    public static JButton getBtnOk() {
        return btnOk;
    }

    public static JList<String> getCategoryList() {
        return categoryList;
    }

    public static DefaultListModel<String> getCategoryListModel() {
        return categoryListModel;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static JPanel getSettingsContainer() {
        return settingsContainer;
    }

    private void initialize() {
        categoryList = new JList<String>();
        categoryListModel = new DefaultListModel<String>();
        categoryListModel.addElement("General");
        categoryListModel.addElement("Chat Rooms");
        categoryListModel.addElement("Import Settings");
        categoryListModel.addElement("Export Settings");
        categoryList.setModel(categoryListModel);
        categoryList.setSelectedIndex(0);
        categoryList.addListSelectionListener(new CategorySelectionListener());
        categoryList.setBounds(0, 0, 117, 354);
        frame.getContentPane().add(categoryList);

        settingsContainer = new JPanel(new CardLayout(0, 0));
        settingsContainer.setBounds(122, 0, 512, 354);
        settingsContainer.add(new GeneralCategory(), "General");
        settingsContainer.add(new ChatRoomsCategory(), "Chat Rooms");
        settingsContainer.add(new ImportCategory(), "Import Settings");
        settingsContainer.add(new ExportCategory(), "Export Settings");
        frame.getContentPane().add(settingsContainer);

        BtnControlListener listener = new BtnControlListener();
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(listener);
        btnCancel.setBounds(545, 363, 89, 23);
        frame.getContentPane().add(btnCancel);

        btnOk = new JButton("OK");
        btnOk.addActionListener(listener);
        btnOk.setBounds(446, 363, 89, 23);
        frame.getContentPane().add(btnOk);

    }
}
