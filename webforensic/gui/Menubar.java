package gui;

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

    JMenu optionsMenu = new JMenu("Options");

    JMenu helpMenu = new JMenu("Help");

    JMenu FunctionMenu = new JMenu("Functions");
    JMenuItem urlsItem = new JMenuItem("Urls");
    JMenuItem downloadItem = new JMenuItem("Download");
    JMenuItem cookieItem = new JMenuItem("Cookie");
    JMenuItem cacheItem = new JMenuItem("Cache");

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
        this.add(optionsMenu);
        this.add(helpMenu);

        this.add(FunctionMenu);
        FunctionMenu.add(urlsItem);
        urlsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelect.setUrlsTable();
                System.out.println("urlsItem clicked..");
            }
        });
        FunctionMenu.add(downloadItem);
        FunctionMenu.add(cookieItem);
        FunctionMenu.add(cacheItem);
    }
}
