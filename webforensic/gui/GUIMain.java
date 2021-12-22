package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GUIMain {
    public static void startGUI(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI(){
        JFrame mainFrame = new JFrame("WebForensic");
        mainFrame.setSize(1600, 900);
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TableSelectionDemo newContentPane = TableSelectionDemo.getInstance();
        newContentPane.setLayout(new BorderLayout());
        newContentPane.addComponentToPane();
        mainFrame.getContentPane().add(newContentPane);

        Menubar mainMenuBar = new Menubar();
        mainFrame.setJMenuBar(mainMenuBar);

        mainFrame.setVisible(true);

        String days = (String)JOptionPane.showInputDialog(newContentPane, "items from the last xx days", "Period Setting",
                JOptionPane.PLAIN_MESSAGE, null, null, "10");

        try{
            newContentPane.timelineTable_model.searchRecord(Integer.valueOf(days));
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        newContentPane.bottom.updateItemsCount(newContentPane.timelineTable_model.getRowCount());
    }
}
