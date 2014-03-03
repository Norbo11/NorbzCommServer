package com.github.norbo11.norbzcomm.settings.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import com.github.norbo11.norbzcomm.settings.SettingsFrame;

public class ExportCategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == ExportCategory.getBtnBrowse()) {
            JFileChooser fc = ExportCategory.getFcPath();
            int response = fc.showOpenDialog(SettingsFrame.getFrame());
            if (response == JFileChooser.APPROVE_OPTION) {
                ExportCategory.getTxtPath().setText(fc.getSelectedFile().getAbsolutePath());
            }
        }
    }

}
