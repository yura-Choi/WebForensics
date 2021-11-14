package gui;

import cache.CacheTableModel;
import cookies.CookiesTableModel;
import downloads.DownloadsTableModel;
import timeline.TimelineTableModel;
import urls.UrlsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableSelectionDemo extends JPanel {

    private static TableSelectionDemo instance = new TableSelectionDemo();
    private TableSelectionDemo() {}

    public static TableSelectionDemo getInstance(){
        return instance;
    }



    private static final long serialVersionUID = 1L;
    TableSorter sorter = TableSorter.getInstance();
    public JTable table = new JTable(sorter);

    public UrlsTableModel urlsTable_model = UrlsTableModel.getInstance();
    public DownloadsTableModel downloadsTable_model = DownloadsTableModel.getInstance();
    public CookiesTableModel cookiesTable_model = CookiesTableModel.getInstance();
    public CacheTableModel cacheTable_model = CacheTableModel.getInstance();
    public TimelineTableModel timelineTable_model = TimelineTableModel.getInstance();

    public TableHeaderSorter tableHeaderSorter = TableHeaderSorter.getInstance();
    static BottomPane bottom;

    void addComponentToPane(){
        JScrollPane center = new JScrollPane(table);
        add(center, BorderLayout.CENTER);
        sorter.setModel(timelineTable_model);
        tableHeaderSorter.install(sorter, table);
        table.setShowGrid(false);

        // setTimelineTable();


        bottom = new BottomPane();
        bottom.init(table.getRowCount());
        bottom.setBackground(Color.WHITE);
        add(bottom, BorderLayout.PAGE_END);

        setupTopPane();
    }

    void setTimelineTable(){
        sorter.setModel(timelineTable_model);
        timelineTable_model.fireTableDataChanged();


        //table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        /*
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
//        if(type())
//        table.getColumnModel().getColumn(7).setPreferredWidth(10);
//        table.getColumnModel().getColumn(8).setPreferredWidth(10);
//        table.getColumnModel().getColumn(9).setPreferredWidth(10);
//        table.getColumnModel().getColumn(10).setPreferredWidth(10);
//        table.getColumnModel().getColumn(11).setPreferredWidth(10);
//        table.getColumnModel().getColumn(12).setPreferredWidth(10);
//        table.getColumnModel().getColumn(13).setPreferredWidth(10);
//        table.getColumnModel().getColumn(14).setPreferredWidth(10);
//        table.getColumnModel().getColumn(15).setPreferredWidth(10);
//        table.getColumnModel().getColumn(16).setPreferredWidth(10);
//        table.getColumnModel().getColumn(17).setPreferredWidth(10);
        table.getTableHeader().setReorderingAllowed(false);
         */
    }

    void setUrlsTable(){
        // urlsTable_model = UrlsTableModel.getInstance();
        // urlsTable_model.fireTableDataChanged();

        // sorter.setModel(urlsTable_model);
        //table.setColumnModel(urlsTableColumnModel);

        sorter.setModel(urlsTable_model);
        urlsTable_model.fireTableDataChanged();

        //table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        /*
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
//        if(type())
//        table.getColumnModel().getColumn(7).setPreferredWidth(10);
//        table.getColumnModel().getColumn(8).setPreferredWidth(10);
//        table.getColumnModel().getColumn(9).setPreferredWidth(10);
//        table.getColumnModel().getColumn(10).setPreferredWidth(10);
//        table.getColumnModel().getColumn(11).setPreferredWidth(10);
//        table.getColumnModel().getColumn(12).setPreferredWidth(10);
//        table.getColumnModel().getColumn(13).setPreferredWidth(10);
//        table.getColumnModel().getColumn(14).setPreferredWidth(10);
//        table.getColumnModel().getColumn(15).setPreferredWidth(10);
//        table.getColumnModel().getColumn(16).setPreferredWidth(10);
//        table.getColumnModel().getColumn(17).setPreferredWidth(10);
        table.getTableHeader().setReorderingAllowed(false);
         */
    }

    void setDownloadsTable(){
        sorter.setModel(downloadsTable_model);
        downloadsTable_model.fireTableDataChanged();
    }

    void setCookiesTable(){
        sorter.setModel(cookiesTable_model);
        cookiesTable_model.fireTableDataChanged();
    }

    void setCacheTable(){
        sorter.setModel(cacheTable_model);
        cacheTable_model.fireTableDataChanged();
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