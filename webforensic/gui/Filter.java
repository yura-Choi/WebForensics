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
    String tableName;

    public UrlsTableModel urlsTable_model = UrlsTableModel.getInstance();
    public DownloadsTableModel downloadsTable_model = DownloadsTableModel.getInstance();
    public CookiesTableModel cookiesTable_model = CookiesTableModel.getInstance();
    public CacheTableModel cacheTable_model = CacheTableModel.getInstance();
    public TimelineTableModel timelineTable_model = TimelineTableModel.getInstance();

    public Filter(String now){
        this.tableName = now;

        if(now.equals("url")){
            check = new JCheckBox[urlsTable_model.getColumnNames().length];
        }
        if(now.equals("download")){
            check = new JCheckBox[downloadsTable_model.getColumnNames().length];
        }
        if(now.equals("cookie")){
            check = new JCheckBox[cookiesTable_model.getColumnNames().length];
        }
        if(now.equals("cache")){
            check = new JCheckBox[cacheTable_model.getColumnNames().length];
        }

        selectAll = new JCheckBox("Select All");
        selectAll.setSelected(true);
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
                        //아무것도 선택 안하면 아래 경고창 띄우고 종료
                        boolean selectSomeThing = false;
                        for (int i=0; i < check.length; i++){
                            if(check[i].isSelected() == true){
                                selectSomeThing = true;
                                break;
                            }
                        }
                        if(selectSomeThing == false){
                            JLabel warning = new JLabel("Select Some Thing!!!");
                            warning.setBounds(80, 100 + 30*check.length + 50, 300, 20);
                            add(warning);
                            setVisible(false);
                            setVisible(true);

                            return;
                        }

                        setVisible(false);
                        boolean[] update = new boolean[check.length];
                        for(int i=0; i < check.length; i++){
                            update[i] = check[i].isSelected();
                        }

                        if(tableName.equals("url")){
                            TableSelectionDemo.getInstance().filter_On.set(0, update);
                            UrlsTableModel.getInstance().fireTableDataChanged();
                        }
                        if(tableName.equals("download")){
                            TableSelectionDemo.getInstance().filter_On.set(1, update);
                            DownloadsTableModel.getInstance().fireTableDataChanged();
                        }
                        if(tableName.equals("cookie")){
                            TableSelectionDemo.getInstance().filter_On.set(2, update);
                            CookiesTableModel.getInstance().fireTableDataChanged();
                        }
                        if(tableName.equals("cache")){
                            TableSelectionDemo.getInstance().filter_On.set(3, update);
                            CacheTableModel.getInstance().fireTableDataChanged();
                        }

                        return;
                    }
                });
                add(button);

                break;
            }
            if(now.equals("url")){
                check[i] = new JCheckBox(urlsTable_model.getColumnName(i));
            }
            if(now.equals("download")){
                check[i] = new JCheckBox(downloadsTable_model.getColumnName(i));
            }
            if(now.equals("cookie")){
                check[i] = new JCheckBox(cookiesTable_model.getColumnName(i));
            }
            if(now.equals("cache")){
                check[i] = new JCheckBox(cacheTable_model.getColumnName(i));
            }
//            check[i] = new JCheckBox("go to " + i);

//            check[i].setText("test" + i);
            check[i].setBounds(80, 100 + 30*i, 300, 20);
            check[i].setSelected(true);
            check[i].addActionListener(this);
            add(check[i]);
        }
        setSize(350, check.length * 30 + 250);
        setLayout(null);
    }

    public void turnOn(){
        setVisible(true);
    }

    //필터 선택하면 전체 선택 해제용
    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectAll.isSelected()){
            selectAll.setSelected(false);
            //setVisible(false);

            return;
        }
    }
}
