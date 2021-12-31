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

public class Filter extends JFrame implements ActionListener {
    JCheckBox[] check;
    JCheckBox selectAll;
    JButton button;

    public UrlsTableModel urlsTable_model = UrlsTableModel.getInstance();
    public DownloadsTableModel downloadsTable_model = DownloadsTableModel.getInstance();
    public CookiesTableModel cookiesTable_model = CookiesTableModel.getInstance();
    public CacheTableModel cacheTable_model = CacheTableModel.getInstance();
    public TimelineTableModel timelineTable_model = TimelineTableModel.getInstance();

    public Filter(String now){
        if(now.equals("url")){
            check = new JCheckBox[urlsTable_model.getColumnCount()];
        }
        if(now.equals("download")){
            check = new JCheckBox[downloadsTable_model.getColumnCount()];
        }
        if(now.equals("cookie")){
            check = new JCheckBox[cookiesTable_model.getColumnCount()];
        }
        if(now.equals("cache")){
            check = new JCheckBox[cacheTable_model.getColumnCount()];
        }

        selectAll = new JCheckBox("Select All");
        selectAll.setBounds(50, 60, 80, 20);
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if allchecked is true, set everything unchecked
                //else all check
//                if(selectAll.isSelected() == false){
//                    return;
//                }

                //checking allchecked
                Boolean allChecked = true;
                for(int i=0; i < check.length; i++){
                    if(check[i].isSelected() == false){
                        allChecked = false;
                        break;
                    }
                }

                //change depend on allchecked
                if(allChecked == true){
                    for(int i=0; i < check.length; i++) {
                        check[i].setSelected(false);
                    }
                }
                else{
                    for(int i=0; i < check.length; i++) {
                        check[i].setSelected(true);
                    }
                }

            }
        });
        add(selectAll);


        for (int i=0; i < check.length + 1; i++){
            // when last, add apply button
            if(i == check.length){
                button = new JButton("Apply");
                button.setBounds(80, 100 + 30*i, 80, 20);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);

                        return;
                    }
                });
                add(button);

                break;
            }
            check[i] = new JCheckBox("go to " + i);

            check[i].setText("test" + i);
            check[i].setBounds(80, 100 + 30*i, 80, 20);

            check[i].addActionListener(this);
            add(check[i]);
        }
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectAll.isSelected()){
            selectAll.setSelected(false);
            //setVisible(false);

            return;
        }
    }
}
