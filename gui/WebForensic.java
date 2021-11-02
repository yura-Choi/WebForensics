package webforensic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WebForensic extends JFrame {

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

    // Toolbar
    JToolBar mainToolBar = new JToolBar();
    JButton optionButton = new JButton(new ImageIcon("./src/webforensic/resources/option.png"));
    JButton saveButton = new JButton(new ImageIcon("./src/webforensic/resources/save.png"));
    JButton deleteButton = new JButton(new ImageIcon("./src/webforensic/resources/delete.png"));
    JButton refreshButton = new JButton(new ImageIcon("./src/webforensic/resources/refresh.png"));
    JButton copyButton = new JButton(new ImageIcon("./src/webforensic/resources/copy.png"));
    JButton propertiesButton = new JButton(new ImageIcon("./src/webforensic/resources/properties.png"));
    JButton findButton = new JButton(new ImageIcon("./src/webforensic/resources/find.png"));
    JButton qrcodeButton = new JButton(new ImageIcon("./src/webforensic/resources/qrcode.png"));
    JButton exitButton = new JButton(new ImageIcon("./src/webforensic/resources/exit.png"));


    // Main Frame
    String header[] = {"학생이름", "국어", "영어", "수학"};
    String contents[][] = {
            {"박영수", "90", "87", "98"},
            {"김영희", "100", "99", "100"},
            {"김철수", "30", "25", "9"},
    };

    JTable table = new JTable(contents, header);
    JScrollPane scrollPane = new JScrollPane(table);


    public static void main(String args[]) {
        // create frame
        new WebForensic().show();
    }

    public WebForensic() {
        // frame constructor
        setTitle("Web Froensic");
        setResizable(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;

        setJMenuBar(mainMenuBar);
        mainMenuBar.add(fileMenu);
        fileMenu.add(loadMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMenuItemActionPerformed(e);
            }
        });
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenuItemActionPerformed(e);
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuItemActionPerformed(e);
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenuItemActionPerformed(e);
            }
        });

        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(optionsMenu);
        mainMenuBar.add(helpMenu);


        mainToolBar.setFloatable(false);
        mainToolBar.setBackground(Color.BLACK);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        //gridConstraints.gridwidth = 8;
        gridConstraints.fill = gridConstraints.HORIZONTAL;
        getContentPane().add(mainToolBar, gridConstraints);

        Dimension bSize = new Dimension(24, 24);
        sizeButton(optionButton, bSize);
        optionButton.setToolTipText("Advanced Options");
        optionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        optionButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(optionButton);
        optionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionButtonActionPerformed(e);
            }
        });

        sizeButton(saveButton, bSize);
        saveButton.setToolTipText("Save Selected Items");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });

        sizeButton(deleteButton, bSize);
        deleteButton.setToolTipText("Delete Selected History Records");
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });

        sizeButton(refreshButton, bSize);
        refreshButton.setToolTipText("Refresh");
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshButtonActionPerformed(e);
            }
        });

        sizeButton(copyButton, bSize);
        copyButton.setToolTipText("Copy Selected Items");
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(copyButton);
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyButtonActionPerformed(e);
            }
        });

        sizeButton(propertiesButton, bSize);
        propertiesButton.setToolTipText("Properties");
        propertiesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        propertiesButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(propertiesButton);
        propertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertiesButtonActionPerformed(e);
            }
        });

        sizeButton(findButton, bSize);
        findButton.setToolTipText("Find");
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(findButton);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findButtonActionPerformed(e);
            }
        });

        sizeButton(qrcodeButton, bSize);
        qrcodeButton.setToolTipText("Display QR Code");
        qrcodeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        qrcodeButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(qrcodeButton);
        qrcodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qrcodeButtonActionPerformed(e);
            }
        });

        sizeButton(exitButton, bSize);
        exitButton.setToolTipText("Exit");
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
       // gridConstraints.gridwidth = 8;
        gridConstraints.fill = gridConstraints.HORIZONTAL;
        getContentPane().add(scrollPane, gridConstraints);


        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    }

    private void sizeButton(JButton b, Dimension d) {
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
    }

    private void optionButtonActionPerformed(ActionEvent e) {

    }

    private void saveButtonActionPerformed(ActionEvent e){

    }

    private void deleteButtonActionPerformed(ActionEvent e){

    }

    private void refreshButtonActionPerformed(ActionEvent e){

    }

    private void copyButtonActionPerformed(ActionEvent e){

    }

    private void propertiesButtonActionPerformed(ActionEvent e){

    }

    private void findButtonActionPerformed(ActionEvent e){

    }

    private void qrcodeButtonActionPerformed(ActionEvent e){

    }

    private void exitButtonActionPerformed(ActionEvent e){

    }


    private void loadMenuItemActionPerformed(ActionEvent e) {

    }

    private void openMenuItemActionPerformed(ActionEvent e) {

    }

    private void saveMenuItemActionPerformed(ActionEvent e) {

    }

    private void exitMenuItemActionPerformed(ActionEvent e) {

    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }
}
