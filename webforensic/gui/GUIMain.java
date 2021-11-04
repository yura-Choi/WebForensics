package webforensic.gui;

import webforensic.facade.DataEngineInterface;

import javax.swing.*;

public class GUIMain {
    static DataEngineInterface engine;
    public static void startGUI(DataEngineInterface en){
        engine = en;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI(){
        JFrame mainFrame = new JFrame("WebForensic");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TableSelectionDemo newContentPane = new TableSelectionDemo();
        newContentPane.addComponentToPane();
        mainFrame.getContentPane().add(newContentPane);

        // menu structure
        JMenuBar mainMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Configuration From File");
        JMenuItem openMenuItem = new JMenuItem("Open URL In Web Browser");
        JMenuItem saveMenuItem = new JMenuItem("Save Configuration To File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        JMenu editMenu = new JMenu("Edit");

        JMenu viewMenu = new JMenu("View");

        JMenu optionsMenu = new JMenu("Options");

        JMenu helpMenu = new JMenu("Help");

        mainFrame.setJMenuBar(mainMenuBar);
        mainMenuBar.add(fileMenu);
        fileMenu.add(loadMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(optionsMenu);
        mainMenuBar.add(helpMenu);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
