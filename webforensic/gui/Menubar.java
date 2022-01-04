package gui;

import urls.UrlsTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Menubar extends JMenuBar {
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("Load Configuration From File");
    JMenuItem openMenuItem = new JMenuItem("Open URL In Web Browser");
    JMenuItem saveMenuItem = new JMenuItem("Save Configuration To File");
    JMenuItem exitMenuItem = new JMenuItem("Exit");


    JMenu editMenu = new JMenu("Edit");

    JMenu viewMenu = new JMenu("View");
    JMenuItem filterItem = new JMenuItem("Filter");
    Filter url = new Filter("url");
    Filter download = new Filter("download");
    Filter cookie = new Filter("cookie");
    Filter cache = new Filter("cache");

    JMenu optionsMenu = new JMenu("Options");

    JMenu helpMenu = new JMenu("Help");

    JMenu FunctionMenu = new JMenu("Functions");
    JMenuItem urlsItem = new JMenuItem("Urls");
    JMenuItem downloadItem = new JMenuItem("Download");
    JMenuItem cookieItem = new JMenuItem("Cookie");
    JMenuItem cacheItem = new JMenuItem("Cache");
    JMenuItem timelineItem = new JMenuItem("Timeline");

    TableSelectionDemo tableSelect;


    public Menubar(){
        tableSelect = TableSelectionDemo.getInstance();

        this.add(fileMenu);
        fileMenu.add(loadMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        this.add(editMenu);
        this.add(viewMenu);
        viewMenu.add(filterItem);
        filterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String current = TableSorter.getInstance().current_table;
//                System.out.printf(current);
                if(current.equals("urls.UrlsTableModel")){
                    url.turnOn();
                }
                if(current.equals("downloads.DownloadsTableModel")){
                    download.turnOn();
                }
                if(current.equals("cookies.CookiesTableModel")){
                    cookie.turnOn();
                }
                if(current.equals("cache.CacheTableModel")){
                    cache.turnOn();
                }
            }
        });

        this.add(optionsMenu);
        this.add(helpMenu);

        this.add(FunctionMenu);
        FunctionMenu.add(urlsItem);
        urlsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setUrlsTable();
            }
        });
        FunctionMenu.add(downloadItem);
        downloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setDownloadsTable();
            }
        });
        FunctionMenu.add(cookieItem);
        cookieItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setCookiesTable();
            }
        });
        FunctionMenu.add(cacheItem);
        cacheItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setCacheTable();
            }
        });
        FunctionMenu.add(timelineItem);
        timelineItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setTimelineTable();
            }
        });

    }
}
