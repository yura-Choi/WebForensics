package gui;

import cache.CacheTableModel;
import cookies.CookiesTableModel;
import downloads.DownloadsTableModel;
import timeline.TimelineTableModel;
import urls.UrlsTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TableSelectionDemo extends JPanel {

    private static TableSelectionDemo instance = new TableSelectionDemo();

    private TableSelectionDemo() {
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
    static BottomPane bottom;

    void addComponentToPane() {
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

    void setTimelineTable() {
        sorter.setModel(timelineTable_model);
        timelineTable_model.fireTableDataChanged();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnSize = table.getColumnCount();
                int row = table.getSelectedRow();
//                System.out.println(table.getSelectedRow());
                String[] clicked = new String[columnSize];
                for(int i = 0; i < columnSize;i++){
                    clicked[i] = (String) table.getModel().getValueAt(row, i);
                }
//                System.out.println(Arrays.toString(clicked));
                JDialog test = new JDialog();
//                Dialog test = new Dialog();

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                //test.add();
//                test.setLayout(new GridBagLayout());
                test.getContentPane().setLayout(new GridBagLayout());
//                test.setContentPane(show(clicked));
                test.getContentPane().add(show(clicked), gbc);
//                test.add(show(clicked));
//                test.setSize(700,500);
                test.setLocation(e.getX(),e.getY());
                test.setVisible(true);

                test.pack();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
//
//        JTable jt = new JTable(timelineTable_model);
//        jt.addMouseListener((MouseListener) this);
//
//        sorter.setModel(jt);


//        timelineTable_model
//        timelineTable_model.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int row = e.getFirstRow();
//                System.out.println(row);
//
//                TimelineTableModel model = (TimelineTableModel) e.getSource();
////                TableModel model = (TableModel) e.getSource();
//                System.out.println(model.getColumnCount());
//            }
//        });
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

    void setUrlsTable() {
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

    void setDownloadsTable() {
        sorter.setModel(downloadsTable_model);
        downloadsTable_model.fireTableDataChanged();
    }

    void setCookiesTable() {
        sorter.setModel(cookiesTable_model);
        cookiesTable_model.fireTableDataChanged();
    }

    void setCacheTable() {
        sorter.setModel(cacheTable_model);
        cacheTable_model.fireTableDataChanged();
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
//        setLayout(new GridBagLayout());
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