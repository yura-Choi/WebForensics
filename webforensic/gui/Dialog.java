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
        if(input[1].equals("urls")){
            row = UrlsTableModel.getInstance().getColumnCount();
            UrlsTableModel.getColumnNames();
        }
        else if(input[1].equals("cookies")){
            row = CookiesTableModel.getInstance().getColumnCount();
        }
        else if(input[1].equals("downloads")){
            row = DownloadsTableModel.getInstance().getColumnCount();
        }
        else {
            row = CacheTableModel.getInstance().getColumnCount();
        }

        for(int i=0;i<row;i++){
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            add(new TextField("test"), gbc);
        }

//        add(tf);
//        add(okButton);
        setSize(1000,1000);
        setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }
}
