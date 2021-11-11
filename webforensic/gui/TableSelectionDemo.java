package gui;

import cookies.CookiesTableModel;
import urls.UrlsTableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class TableSelectionDemo extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    static BottomPane bottom;
    public TableSelectionDemo(){
        super(new BorderLayout());
    }

    void addComponentToPane(){
        UrlsTableModel table_model = null;
//        CookiesTableModel table_model = null;
        try{
            table_model = new UrlsTableModel(10);
//            table_model = new CookiesTableModel(10);

            TableSorter sorter = new TableSorter(table_model);

            table = new JTable(sorter);
            TableHeaderSorter.install(sorter, table);


        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        JScrollPane center = new JScrollPane(table);
        add(center, BorderLayout.CENTER);

        table.setShowGrid(false);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        if(table_model.getClass().getName().equals("CookiesTableModel")){
            table.getColumnModel().getColumn(7).setPreferredWidth(10);
            table.getColumnModel().getColumn(8).setPreferredWidth(10);
            table.getColumnModel().getColumn(9).setPreferredWidth(10);
            table.getColumnModel().getColumn(10).setPreferredWidth(10);
            table.getColumnModel().getColumn(11).setPreferredWidth(10);
            table.getColumnModel().getColumn(12).setPreferredWidth(10);
            table.getColumnModel().getColumn(13).setPreferredWidth(10);
            table.getColumnModel().getColumn(14).setPreferredWidth(10);
            table.getColumnModel().getColumn(15).setPreferredWidth(10);
            table.getColumnModel().getColumn(16).setPreferredWidth(10);
            table.getColumnModel().getColumn(17).setPreferredWidth(10);
        }
        table.getTableHeader().setReorderingAllowed(false);

        bottom = new BottomPane();
        bottom.init(table.getRowCount());
        bottom.setBackground(Color.WHITE);
        add(bottom, BorderLayout.PAGE_END);

        setupTopPane();
    }

    void setupTopPane(){

        // Toolbar
        JToolBar mainToolBar = new JToolBar();
        JButton optionButton = new JButton(new ImageIcon("./webforensic/resources/option.png"));
        JButton saveButton = new JButton(new ImageIcon("./webforensic/resources/save.png"));
        JButton deleteButton = new JButton(new ImageIcon("./webforensic/resources/delete.png"));
        JButton refreshButton = new JButton(new ImageIcon("./webforensic/resources/refresh.png"));
        JButton copyButton = new JButton(new ImageIcon("./webforensic/resources/copy.png"));
        JButton propertiesButton = new JButton(new ImageIcon("./webforensic/resources/properties.png"));
        JButton findButton = new JButton(new ImageIcon("./webforensic/resources/find.png"));
        JButton qrcodeButton = new JButton(new ImageIcon("./webforensic/resources/qrcode.png"));
        JButton exitButton = new JButton(new ImageIcon("./webforensic/resources/exit.png"));

        mainToolBar.setFloatable(false);
        mainToolBar.setBackground(Color.WHITE);

        Dimension bSize = new Dimension(24, 24);

        sizeButton(optionButton, bSize);
        optionButton.setBorderPainted(false);
        optionButton.setOpaque(false);
        optionButton.setToolTipText("Advanced Options");
        optionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        optionButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(optionButton);

        sizeButton(saveButton, bSize);
        saveButton.setBorderPainted(false);
        saveButton.setOpaque(false);
        saveButton.setToolTipText("Save Selected Items");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(saveButton);

        sizeButton(deleteButton, bSize);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.setToolTipText("Delete Selected History Records");
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(deleteButton);

        sizeButton(refreshButton, bSize);
        refreshButton.setBorderPainted(false);
        refreshButton.setOpaque(false);
        refreshButton.setToolTipText("Refresh");
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(refreshButton);

        sizeButton(copyButton, bSize);
        copyButton.setBorderPainted(false);
        copyButton.setOpaque(false);
        copyButton.setToolTipText("Copy Selected Items");
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(copyButton);

        sizeButton(propertiesButton, bSize);
        propertiesButton.setBorderPainted(false);
        propertiesButton.setOpaque(false);
        propertiesButton.setToolTipText("Properties");
        propertiesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        propertiesButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(propertiesButton);

        sizeButton(findButton, bSize);
        findButton.setBorderPainted(false);
        findButton.setOpaque(false);
        findButton.setToolTipText("Find");
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(findButton);

        sizeButton(qrcodeButton, bSize);
        qrcodeButton.setBorderPainted(false);
        qrcodeButton.setOpaque(false);
        qrcodeButton.setToolTipText("Display QR Code");
        qrcodeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        qrcodeButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(qrcodeButton);

        sizeButton(exitButton, bSize);
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(false);
        exitButton.setToolTipText("Exit");
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        // topPane.add(mainToolBar, BorderLayout.PAGE_START);



        /*
        JButton search = new JButton("검색");
        topPane.add(search, BorderLayout.LINE_END);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("검색")){
                    tableController.loadData(kwdTextField.getText());
                }
            }
        });

        */
        add(mainToolBar, BorderLayout.PAGE_START);
    }

    private void sizeButton(JButton b, Dimension d) {
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
    }

    private void exitButtonActionPerformed(ActionEvent e){
        System.exit(0);
    }

}