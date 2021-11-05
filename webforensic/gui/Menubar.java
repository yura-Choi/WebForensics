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

    // jdbc exer
    Connection conn;
    Statement stmt;

    public Menubar(){
        this.add(fileMenu);
        fileMenu.add(loadMenuItem);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {loadMenuItemActionPerformed(e);}
        });

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        this.add(editMenu);
        this.add(viewMenu);
        this.add(optionsMenu);
        this.add(helpMenu);
    }

    private void loadMenuItemActionPerformed(ActionEvent e){
        JFileChooser openChooser = new JFileChooser();
        openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        openChooser.setDialogTitle("Open History File");



        if(openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            try {
                String url = "jdbc:sqlite:" + openChooser.getSelectedFile();
                System.out.println(url);

                try {
                    Class.forName("org.sqlite.JDBC");
                }
                catch(ClassNotFoundException er)  {
                    System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
                }

                try{
                    conn = DriverManager.getConnection(url);
                    stmt = conn.createStatement();
                    System.out.println("Connection to SQLite has been established.");
                } catch(SQLException er){
                    System.out.println("Connection failed");
                }

                ArrayList<String[]> result = SQLResult("SELECT title FROM urls");
                int retSz = result.size()-1;
                for(int i=1; i<retSz+1; i++){
                    System.out.println(result.get(i)[0]);
                }



                JOptionPane.showConfirmDialog(null, openChooser.getSelectedFile() + " Opend", "Success !",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

            }
            catch (Exception ex){
                JOptionPane.showConfirmDialog(null, "Error reading in input file - make sure file is correct format",
                        "History File Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
                return;
            }
        }
    }

    private ArrayList<String[]> SQLResult(String sql) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(sql);
        ResultSetMetaData meta = rs.getMetaData();
        int attributesNumber = meta.getColumnCount();

        ArrayList<String[]> result = new ArrayList<String[]>();

        String[] columnHead = new String[attributesNumber];
        for(int i=1; i <= attributesNumber; i++){
            columnHead[i-1] = meta.getColumnName(i);
        }
        result.add(columnHead);

        while(rs.next()) {
            String[] tuple = new String[attributesNumber];
            for(int i=1; i <= meta.getColumnCount(); i++){
                tuple[i-1] = rs.getString(i);
            }
            result.add(tuple);
        }

        rs.close();

        return result;
    }


}
