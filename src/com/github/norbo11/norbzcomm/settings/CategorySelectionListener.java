package com.github.norbo11.norbzcomm.settings;

import java.awt.CardLayout;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CategorySelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent event) {
        // Switch to the card containing the selected category. The card names are the same as the category names on the list.
        CardLayout layout = (CardLayout) SettingsFrame.getSettingsContainer().getLayout();
        layout.show(SettingsFrame.getSettingsContainer(), SettingsFrame.getCategoryList().getSelectedValue());
    }

}
