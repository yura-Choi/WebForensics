package gui;

import cache.CacheTableModel;
import cookies.CookiesTableModel;
import downloads.DownloadsTableModel;
import urls.UrlsTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
    JTextField tf = new JTextField(10);
    JButton okButton = new JButton("OK");

    public Dialog(String[] input){
        super();
        setLayout(new GridBagLayout());
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

//        for(int i=0;i<row;i++){
//            gbc.gridx = 0;
//            gbc.gridy = i;
//            gbc.gridwidth = 1;
//            gbc.gridheight = 1;
//            gbc.fill = GridBagConstraints.VERTICAL;
//            gbc.weightx = 2;
//
//            add(new JLabel(names[i]), gbc);
//            gbc.gridx = 1;
//            gbc.gridy = i;
//            gbc.gridwidth = 1;
//            gbc.gridheight = 1;;
//            gbc.fill = GridBagConstraints.VERTICAL;
//            gbc.weightx = 8;
//
//            add(new JLabel(searched_detail[i]), gbc);
//        }

        String[][] contents = new String[row][2];

        for(int i=0; i<row;i++){
            contents[i][0] = names[i];
            contents[i][1] = searched_detail[i];
        }

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        DefaultTableModel dtm = new DefaultTableModel(contents,header);

        JTable j = new JTable(dtm);
        JScrollPane sp = new JScrollPane(j);
//        JList rowheader = new JList()
//        sp.setRowHeaderView(j.getRowHeight());

        add(sp, gbc);

//        add(tf);
//        add(okButton);

        setSize(700,500);
        setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }
}
