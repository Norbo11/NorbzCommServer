package com.github.norbo11.norbzcomm.settings.export;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExportCategory extends JPanel {

    public ExportCategory() {
        setLayout(null);

        ExportCategoryListener listener = new ExportCategoryListener();
        cbExport = new JCheckBox("Always export settings upon clicking OK");
        cbExport.addActionListener(listener);
        cbExport.setBounds(6, 7, 438, 23);
        add(cbExport);

        JLabel lblExportPath = new JLabel("Export path:");
        lblExportPath.setBounds(10, 37, 95, 14);
        add(lblExportPath);

        txtPath = new JTextField();
        txtPath.setBounds(115, 34, 230, 20);
        add(txtPath);
        txtPath.setColumns(10);

        btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(listener);
        btnBrowse.setBounds(355, 33, 89, 23);
        add(btnBrowse);

        btnExport = new JButton("Export");
        btnExport.addActionListener(listener);
        btnExport.setBounds(6, 62, 89, 23);
        add(btnExport);

        fcPath = new JFileChooser();
    }

    private static final long serialVersionUID = 1L;
    private static JTextField txtPath;
    private static JButton btnBrowse;
    private static JButton btnExport;
    private static JCheckBox cbExport;

    private static JFileChooser fcPath;

    public static JButton getBtnBrowse() {
        return btnBrowse;
    }

    public static JButton getBtnExport() {
        return btnExport;
    }

    public static JCheckBox getCbExport() {
        return cbExport;
    }

    public static JFileChooser getFcPath() {
        return fcPath;
    }

    public static JTextField getTxtPath() {
        return txtPath;
    }
}
