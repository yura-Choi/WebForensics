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
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class TableSelectionDemo extends JPanel {

    private static TableSelectionDemo instance = new TableSelectionDemo();

    private TableSelectionDemo() {
        filter_On.add(new boolean[urlsTable_model.getColumnNames().length]);
        Arrays.fill(filter_On.get(0), true);
        filter_On.add(new boolean[downloadsTable_model.getColumnNames().length]);
        Arrays.fill(filter_On.get(1), true);
        filter_On.add(new boolean[cookiesTable_model.getColumnNames().length]);
        Arrays.fill(filter_On.get(2), true);
        filter_On.add(new boolean[cacheTable_model.getColumnNames().length]);
        Arrays.fill(filter_On.get(3), true);
    }

    public static TableSelectionDemo getInstance() {
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
    public BottomPane bottom = BottomPane.getInstance();

    public ArrayList<boolean[]> filter_On = new ArrayList<boolean[]>();

    void addComponentToPane() {
        JScrollPane center = new JScrollPane(table);
        add(center, BorderLayout.CENTER);

        bottom.setBackground(Color.WHITE);
        add(bottom, BorderLayout.PAGE_END);

        setTimelineTable();
        tableHeaderSorter.install(sorter, table);
        table.setShowGrid(false);

        setupTopPane();
    }

    void setDetailDialog(MouseEvent e){
        int columnSize = table.getColumnCount();
        int row = table.getSelectedRow();
        String[] clicked = new String[columnSize];
        for(int i = 0; i < columnSize;i++){
            clicked[i] = (String) table.getModel().getValueAt(row, i);
        }
        JDialog test = new JDialog();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        test.getContentPane().setLayout(new GridBagLayout());
        test.getContentPane().add(show(clicked), gbc);
        test.setLocation(e.getXOnScreen(),e.getYOnScreen());
        test.setVisible(true);

        test.pack();
    }

    void setTimelineTable() {
        sorter.setModel(timelineTable_model);
        timelineTable_model.fireTableDataChanged();
        bottom.updateItemsCount(table.getRowCount());
    }

    void setUrlsTable() {
        sorter.setModel(urlsTable_model);
        urlsTable_model.fireTableDataChanged();
        bottom.updateItemsCount(table.getRowCount());
    }

    void setDownloadsTable() {
        sorter.setModel(downloadsTable_model);
        downloadsTable_model.fireTableDataChanged();
        bottom.updateItemsCount(table.getRowCount());
    }

    void setCookiesTable() {
        sorter.setModel(cookiesTable_model);
        cookiesTable_model.fireTableDataChanged();
        bottom.updateItemsCount(table.getRowCount());
    }

    void setCacheTable() {
        sorter.setModel(cacheTable_model);
        cacheTable_model.fireTableDataChanged();
        bottom.updateItemsCount(table.getRowCount());
    }

    void setupTopPane() {

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

    private void exitButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }


    public JPanel show(String[] input){
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int row;
        String[] header = {"type","data"};
        String[] names = null;
        String[] searched_detail = null;

        if(input[1].equals("urls")){
            row = UrlsTableModel.getInstance().getColumnCount();
            names = UrlsTableModel.getColumnNames();
            searched_detail = UrlsTableModel.getInstance().detail(input[2], input[3]);
        }
        else if(input[1].equals("cookies")){
            row = CookiesTableModel.getInstance().getColumnCount();
            names = CookiesTableModel.getColumnNames();
            searched_detail = CookiesTableModel.getInstance().detail(input[2], input[3]);
        }
        else if(input[1].equals("downloads")){
            row = DownloadsTableModel.getInstance().getColumnCount();
            names = DownloadsTableModel.getColumnNames();
            searched_detail = DownloadsTableModel.getInstance().detail(input[2], input[3]);
        }
        else {
            row = CacheTableModel.getInstance().getColumnCount();
            names = CacheTableModel.getInstance().getColumnNames();
            searched_detail = CacheTableModel.getInstance().detail(input[2], input[3]);
        }

        int bonus = 0;
        for(int i=0;i<row + bonus;i++) {
            if (searched_detail[i - bonus].length() > 100) {
                ArrayList<String> seperate = Seperate(searched_detail[i - bonus]);
                for(int j=0; j < seperate.size(); j++){
                    i++;
                    bonus++;
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.weightx = 2;
                    if(j == 0){
                        pan.add(new JLabel(names[i - bonus]), gbc);
                    }
                    else {
                        pan.add(new JLabel(""), gbc);
                    }
                    gbc.gridx = 1;
                    gbc.gridy = i;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.weightx = 8;
                    pan.add(new JLabel(seperate.get(j)), gbc);

                }


            } else {
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.fill = GridBagConstraints.VERTICAL;
                gbc.weightx = 2;
                pan.add(new JLabel(names[i - bonus]), gbc);

                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.fill = GridBagConstraints.VERTICAL;
                gbc.weightx = 8;
                pan.add(new JLabel(searched_detail[i - bonus]), gbc);

            }
        }
        return pan;
    }

    public ArrayList<String> Seperate(String bigOne){
        ArrayList<String> ret = new ArrayList<String>();

        int size = 100;
        int count = 0;
        for(int max = bigOne.length(); max > 0; max -= size){
//            System.out.printf("count %d\n", count);
            if((count+1)*size > bigOne.length()){
                ret.add(bigOne.substring(count*size));
            }
            else{
                ret.add(bigOne.substring(count*size, (count+1)*size));
            }
            count++;
        }

//        System.out.printf("seperate end\n");
        return ret;
    }
}