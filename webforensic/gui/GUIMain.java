package gui;

import javax.swing.*;

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
        mainFrame.setSize(1100, 600);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TableSelectionDemo newContentPane = new TableSelectionDemo();
        newContentPane.addComponentToPane();
        mainFrame.getContentPane().add(newContentPane);

        Menubar mainMenuBar = new Menubar();
        mainFrame.setJMenuBar(mainMenuBar);

        //mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
