package gui;

import cache.CacheTableModel;
import cookies.CookiesTableModel;
import downloads.DownloadsTableModel;
import urls.UrlsTableModel;

import javax.swing.*;
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

        for(int i=0;i<row;i++){
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            add(new TextField(names[i]), gbc);
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            add(new TextField(searched_detail[i]), gbc);
        }

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
